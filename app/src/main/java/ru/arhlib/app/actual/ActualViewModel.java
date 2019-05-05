package ru.arhlib.app.actual;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arhlib.app.data.Api;
import ru.arhlib.app.data.Webservice;

public class ActualViewModel extends ViewModel {

    public MutableLiveData<List<Actual>> actual;
    public ObservableBoolean showError;
    public ObservableBoolean showProgressBar;
    public ObservableBoolean showPullToRefresh;
    public MutableLiveData<Boolean> showErrorSnackbar;
    private Webservice webservice;

    public ActualViewModel() {
        actual = new MutableLiveData<>();
        showError = new ObservableBoolean();
        showProgressBar = new ObservableBoolean();
        showPullToRefresh = new ObservableBoolean();
        showErrorSnackbar = new MutableLiveData<>();
        webservice = Api.getInstance();

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
        webservice.getActual().enqueue(new Callback<List<Actual>>() {
            @Override
            public void onResponse(Call<List<Actual>> call, Response<List<Actual>> response) {
                stopLoading();
                if (response.isSuccessful()) {
                    actual.postValue(response.body());
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Actual>> call, Throwable t) {
                stopLoading();
                showError();
            }
        });
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
