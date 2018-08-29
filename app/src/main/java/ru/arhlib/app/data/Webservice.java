package ru.arhlib.app.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.arhlib.app.afisha.Page;
import ru.arhlib.app.news.Post;

public interface Webservice {
    @GET("pages/{page}")
    Call<Page> getPage(@Path("page") int pageId);

    @GET("posts?_embed&per_page=20")
    Call<List<Post>> getPosts(@Query("page") int page);

    @GET("posts?_embed")
    Call<List<Post>> searchPosts(@Query("search") String search, @Query("page") int page);
}
