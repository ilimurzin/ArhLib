package ru.arhlib.app.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
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
        val instance = create("https://arhlib.ru/wp-json/".toHttpUrl())

        fun create(httpUrl: HttpUrl): Webservice {
            val mediaType = "application/json".toMediaType()
            val json = Json { ignoreUnknownKeys = true }
            return Retrofit.Builder()
                    .baseUrl(httpUrl)
                    .addConverterFactory(json.asConverterFactory(mediaType))
                    .build()
                    .create(Webservice::class.java)
        }
    }
}
