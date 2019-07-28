package ru.arhlib.app.actual;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;
import ru.arhlib.app.data.Api;
import ru.arhlib.app.data.Webservice;
import ru.arhlib.app.news.Post;

public class ActualViewModel extends ViewModel {

    public MutableLiveData<List<ActualItem>> actual;
    public ObservableBoolean showError;
    public ObservableBoolean showProgressBar;
    public ObservableBoolean showPullToRefresh;
    public MutableLiveData<Boolean> showErrorSnackbar;
    private Webservice webservice;
    private Executor executor;

    public ActualViewModel() {
        actual = new MutableLiveData<>();
        showError = new ObservableBoolean();
        showProgressBar = new ObservableBoolean();
        showPullToRefresh = new ObservableBoolean();
        showErrorSnackbar = new MutableLiveData<>();
        webservice = Api.getInstance();
        executor = Executors.newSingleThreadExecutor();

        showProgressBar.set(true);
        fetch();
    }

    public void onSwipeRefresh() {
        showPullToRefresh.set(true);
        fetch();
    }

    public void onButtonClick(View view) {
        showProgressBar.set(true);
        fetch();
    }

    private void fetch() {
        showError.set(false);

        executor.execute(() -> {
            try {
                List<ActualItem> actualItemList = new ArrayList<>();
                actualItemList.addAll(getActual());
                actualItemList.addAll(getStickyPosts());
                actual.postValue(actualItemList);
            } catch (IOException e) {
                showError();
            } catch (RuntimeException e) {
                showError();
            } finally {
                stopLoading();
            }
        });
    }

    private List<Actual> getActual() throws IOException, RuntimeException {
        Response<List<Actual>> response = webservice.getActual().execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("Server error " + response.code());
        }
        return response.body();
    }

    private List<Post> getStickyPosts() throws IOException, RuntimeException {
        Response<List<Post>> response = webservice.getStickyPosts().execute();
        if (!response.isSuccessful()) {
            throw new RuntimeException("Server error " + response.code());
        }
        return response.body();
    }

    private void stopLoading() {
        showProgressBar.set(false);
        showPullToRefresh.set(false);
    }

    private void showError() {
        if (actual.getValue() == null) {
            showError.set(true);
        } else {
            showErrorSnackbar.postValue(true);
        }
    }
}
