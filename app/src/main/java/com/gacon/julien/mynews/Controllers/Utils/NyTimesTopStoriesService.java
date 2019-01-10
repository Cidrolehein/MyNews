package com.gacon.julien.mynews.Controllers.Utils;


import com.gacon.julien.mynews.Controllers.Activities.MainActivity;
import com.gacon.julien.mynews.Models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.Models.Result;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NyTimesTopStoriesService {

    @GET("{section}.json?api-key=6587436eb3cd41d783930e3cc555da36")
    Call<MainNewYorkTimesTopStories> getNyTopStories(@Path("section") String section);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
