package com.gacon.julien.mynews.Controllers.ClasseUtils;

import com.gacon.julien.mynews.Models.GithubUserInfo;
import com.gacon.julien.mynews.Models.SearchArticleNewYorker;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleNewYorkerService {
    @GET("users/{username}/following")
    Observable<List<SearchArticleNewYorker>> getFollowing(@Path("username") String username);

     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("/users/{username}")
    Observable<GithubUserInfo> getUserInfos(@Path("username") String username);
}
