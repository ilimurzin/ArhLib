package ru.arhlib.app.afisha

sealed class LoadResult<out R> {
    class Success<T>(val data: T) : LoadResult<T>()
    object Loading : LoadResult<Nothing>()
    object Error : LoadResult<Nothing>()
}
