package ru.arhlib.app.news;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arhlib.app.MainActivity;
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
                            postDao.delete();
                            postDao.save(response.body());
                        }).start();
                    }
                }

                @Override
                public void onFailure(Call<List<Post>> call, Throwable t) {
                    MainActivity.webserviceErrorSnackbar.show();
                    call.clone().enqueue(this);
                }
            });
        }
        return posts;
    }
}