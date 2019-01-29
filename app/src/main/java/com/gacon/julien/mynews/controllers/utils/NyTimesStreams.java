package com.gacon.julien.mynews.controllers.utils;

import com.gacon.julien.mynews.models.mostPopular.NyApiMostPopular;
import com.gacon.julien.mynews.models.topStories.MainNewYorkTimesTopStories;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NyTimesStreams {

    public static Observable<MainNewYorkTimesTopStories> streamFetchTopStories(String section) {
        NyTimesServices nyService = NyTimesServices.retrofitTopStories.create(NyTimesServices.class);
        return nyService.getNyTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NyApiMostPopular> streamFetchMostPopular(int period) {
        NyTimesServices nyService = NyTimesServices.retrofitMostPopular.create(NyTimesServices.class);
        return nyService.getNyMostPopular(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
