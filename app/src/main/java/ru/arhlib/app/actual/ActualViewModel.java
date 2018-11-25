package ru.arhlib.app.actual;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.arhlib.app.data.Api;
import ru.arhlib.app.data.Webservice;

public class ActualViewModel extends ViewModel {

    private MutableLiveData<List<Actual>> actual;
    private Webservice webservice;

    public ActualViewModel() {
        webservice = Api.getInstance();
    }

    public LiveData<List<Actual>> getActual() {
        if (actual == null) {
            actual = new MutableLiveData<>();
            webservice.getActual().enqueue(new Callback<List<Actual>>() {
                @Override
                public void onResponse(Call<List<Actual>> call, Response<List<Actual>> response) {
                    if (response.isSuccessful()) {
                        actual.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Actual>> call, Throwable t) {
                    call.clone().enqueue(this);
                }
            });
        }
        return actual;
    }
}
