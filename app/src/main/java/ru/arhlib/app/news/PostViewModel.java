package ru.arhlib.app.news;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arhlib.app.data.Api;
import ru.arhlib.app.data.AppDatabase;
import ru.arhlib.app.data.Webservice;

public class PostViewModel extends AndroidViewModel {
    private LiveData<List<Post>> posts;
    private PostDao postDao;
    private Webservice webservice;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postDao = AppDatabase.getInstance(application).postDao();
        webservice = Api.getInstance();
    }

    public LiveData<List<Post>> getPosts() {
        if (posts == null) {
            posts = postDao.load();

            webservice.getPosts(1).enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                    if (response.isSuccessful()) {
                        new Thread(() -> {
                            postDao.save(response.body());
                        }).start();
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        }
        return posts;
    }
}
