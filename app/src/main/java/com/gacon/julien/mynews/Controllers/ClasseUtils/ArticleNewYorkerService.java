package com.gacon.julien.mynews.Controllers.ClasseUtils;

import com.gacon.julien.mynews.Models.SearchArticleNewYorker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleNewYorkerService {
    @GET("users/{username}/following")
    Call<List<SearchArticleNewYorker>> getFollowing(@Path("username") String username);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
