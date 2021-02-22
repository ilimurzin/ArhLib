package ru.arhlib.app.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.arhlib.app.actual.ActualLink
import ru.arhlib.app.afisha.Page
import ru.arhlib.app.news.Post

interface Webservice {
    @GET("wp/v2/pages/{page}")
    suspend fun getPage(@Path("page") pageId: Int): Page

    @GET("wp/v2/posts?_embed&per_page=20")
    suspend fun getPosts(@Query("page") page: Int): List<Post>

    @GET("wp/v2/posts?_embed&per_page=20&sticky=true")
    suspend fun getStickyPosts(): List<Post>

    @GET("imp/v1/actual")
    suspend fun getActualLinks(): List<ActualLink>
}
