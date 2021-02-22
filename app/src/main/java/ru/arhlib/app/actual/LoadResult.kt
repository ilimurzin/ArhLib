package ru.arhlib.app.actual

sealed class LoadResult<out R> {
    class Success<T>(val data: T) : LoadResult<T>()
    object Loading : LoadResult<Nothing>()
    object Refreshing : LoadResult<Nothing>()
    object Error : LoadResult<Nothing>()
}
