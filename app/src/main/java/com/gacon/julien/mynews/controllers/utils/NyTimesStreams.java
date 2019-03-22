package com.gacon.julien.mynews.controllers.utils;

import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.models.SearchApiResult;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Creat a stream with observable
 */

public class NyTimesStreams {

    public static Observable<MainNewYorkTimesTopStories> streamFetchTopStories(String section) {
        NyTimesServices nyService = NyTimesServices.retrofitTopStories.create(NyTimesServices.class);
        return nyService.getNyTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<MainNewYorkTimesTopStories> streamFetchMostPopular(int period) {
        NyTimesServices nyService = NyTimesServices.retrofitMostPopular.create(NyTimesServices.class);
        return nyService.getNyMostPopular(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<SearchApiResult> streamFetchSearch(String beginDate, String endDate, String filter, String query, int page, String sort, String apiKey) {
        NyTimesServices nyService = NyTimesServices.retrofitSearch.create(NyTimesServices.class);
        return nyService.getSearchArticle(beginDate, endDate, filter, page, query, sort, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
