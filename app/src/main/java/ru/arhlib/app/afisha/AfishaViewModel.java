package ru.arhlib.app.afisha;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arhlib.app.data.Api;
import ru.arhlib.app.data.AppDatabase;
import ru.arhlib.app.data.Webservice;

public class AfishaViewModel extends AndroidViewModel {
    private LiveData<Page> page;
    private PageDao pageDao;
    private Webservice webservice;

    public AfishaViewModel(@NonNull Application application) {
        super(application);
        pageDao = AppDatabase.getInstance(application).pageDao();
        webservice = Api.getInstance();
    }

    public LiveData<Page> getPage(int pageId) {
        if (page == null) {
            page = pageDao.load(pageId);

            webservice.getPage(pageId).enqueue(new Callback<Page>() {
                @Override
                public void onResponse(Call<Page> call, Response<Page> response) {
                    if (response.isSuccessful()) {
                        new Thread(() -> {
                            pageDao.save(response.body());
                        }).start();
                    }
                }

                @Override
                public void onFailure(Call<Page> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        }
        return page;
    }
}
