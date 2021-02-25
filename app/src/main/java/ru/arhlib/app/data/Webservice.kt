package ru.arhlib.app.data

import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    companion object {
        val instance = create(HttpUrl.get("https://arhlib.ru/wp-json/"))

        fun create(httpUrl: HttpUrl): Webservice {
            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(Webservice::class.java)
        }
    }
}
