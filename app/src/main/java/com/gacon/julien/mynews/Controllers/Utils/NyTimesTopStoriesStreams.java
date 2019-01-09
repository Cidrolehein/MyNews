package com.gacon.julien.mynews.Controllers.Utils;

import com.gacon.julien.mynews.Models.MainNewYorkTimesTopStories;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NyTimesTopStoriesStreams {

    public static Observable<MainNewYorkTimesTopStories> streamFetchTopStories(String section) {
        NyTimesTopStoriesService nyService = NyTimesTopStoriesService.retrofit.create(NyTimesTopStoriesService.class);
        return nyService.getNyTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
