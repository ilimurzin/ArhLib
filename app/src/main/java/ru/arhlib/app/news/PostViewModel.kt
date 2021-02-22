package ru.arhlib.app.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ru.arhlib.app.data.Api
import ru.arhlib.app.data.Webservice

class PostViewModel : ViewModel() {
    private val webservice: Webservice = Api.getInstance()

    val flow = Pager(PagingConfig(pageSize = 20)) { PostPagingSource(webservice) }
            .flow
            .cachedIn(viewModelScope)
}
