package ru.arhlib.app.afisha

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.arhlib.app.data.Webservice

class AfishaViewModel : ViewModel() {
    private val webservice: Webservice = Webservice.instance

    val afisha = MutableLiveData<LoadResult<Page>>()

    init {
        load()
    }

    fun load() {
        afisha.postValue(LoadResult.Loading)

        viewModelScope.launch {
            try {
                afisha.postValue(LoadResult.Success(getPage()))
            } catch (throwable: Throwable) {
                afisha.postValue(LoadResult.Error)
            }
        }
    }

    private suspend fun getPage(): Page {
        return webservice.getPage(6671)
    }
}
