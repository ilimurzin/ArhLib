package ru.arhlib.app.actual

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.arhlib.app.data.Webservice

class ActualViewModel : ViewModel() {
    private val webservice: Webservice = Webservice.instance

    val actualItems = MutableLiveData<LoadResult<List<ActualItem>>>()

    init {
        load()
    }

    fun load() {
        actualItems.postValue(LoadResult.Loading)

        launchBackgroundRefresh()
    }

    private fun launchBackgroundRefresh() {
        viewModelScope.launch {
            try {
                actualItems.postValue(LoadResult.Success(getActualItems()))
            } catch (throwable: Throwable) {
                actualItems.postValue(LoadResult.Error)
            }
        }
    }

    private suspend fun getActualItems(): List<ActualItem> {
        return webservice.getActualLinks() + webservice.getStickyPosts();
    }

    fun refresh() {
        actualItems.postValue(LoadResult.Refreshing)

        launchBackgroundRefresh()
    }
}
