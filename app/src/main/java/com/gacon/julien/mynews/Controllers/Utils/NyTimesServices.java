package com.gacon.julien.mynews.Controllers.Utils;

import com.gacon.julien.mynews.Models.MostPopular.NyApiMostPopular;
import com.gacon.julien.mynews.Models.TopStories.MainNewYorkTimesTopStories;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NyTimesServices {

    @GET("{section}.json?api-key=KzYIpjPOMj98klY5cukvyxBmBhzKwDKO")
    Observable<MainNewYorkTimesTopStories> getNyTopStories(@Path("section") String section);

    Retrofit retrofitTopStories = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @GET("{period}.json?api-key=KzYIpjPOMj98klY5cukvyxBmBhzKwDKO")
    Observable<NyApiMostPopular> getNyMostPopular(@Path("period") int period);

    Retrofit retrofitMostPopular = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/viewed/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
