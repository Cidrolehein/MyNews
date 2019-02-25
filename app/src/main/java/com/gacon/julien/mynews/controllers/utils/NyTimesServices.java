package com.gacon.julien.mynews.controllers.utils;

import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.models.SearchApiResult;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NyTimesServices {

    @GET("{section}.json?api-key=KzYIpjPOMj98klY5cukvyxBmBhzKwDKO")
    Observable<MainNewYorkTimesTopStories> getNyTopStories(@Path("section") String section);

    Retrofit retrofitTopStories = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("{period}.json?api-key=KzYIpjPOMj98klY5cukvyxBmBhzKwDKO")
    Observable<MainNewYorkTimesTopStories> getNyMostPopular(@Path("period") int period);

    Retrofit retrofitMostPopular = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/viewed/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("articlesearch.json")
    Observable<SearchApiResult> getSearchArticle(@Query("begin_date") String beginDate,
                                                 @Query("end_date") String endDate,
                                                 @Query("fq") String filter,
                                                 @Query("page") int page,
                                                 @Query("q") String query,
                                                 @Query("sort") String sort,
                                                 @Query("api-key") String apiKey);

    Retrofit retrofitSearch = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/search/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
