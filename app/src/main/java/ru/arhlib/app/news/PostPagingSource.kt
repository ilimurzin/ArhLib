package ru.arhlib.app.news

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.arhlib.app.data.Webservice

class PostPagingSource(
        private val webservice: Webservice,
) : PagingSource<Int, Post>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        try {
            val nextPageNumber = params.key ?: 1;
            val response = webservice.getPosts(nextPageNumber)
            return LoadResult.Page(
                    data = response,
                    prevKey = null,
                    nextKey = nextPageNumber + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
