package ru.arhlib.app.afisha

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.arhlib.app.data.Api
import ru.arhlib.app.data.Webservice

class AfishaViewModel : ViewModel() {
    private val webservice: Webservice = Api.getInstance()

    val afisha = MutableLiveData<LoadResult<Page>>()

    init {
        refresh()
    }

    fun refresh() {
        afisha.postValue(LoadResult.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = webservice.getPage(6671).execute()
                if (response.isSuccessful) {
                    afisha.postValue(LoadResult.Success(response.body()!!))
                } else {
                    afisha.postValue(LoadResult.Error)
                }
            } catch (throwable: Throwable) {
                afisha.postValue(LoadResult.Error)
            }
        }
    }
}
