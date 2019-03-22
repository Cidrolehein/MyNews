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

    /**
     * Stream Top Sotires
     * @param section Section
     * @return NyTopStories section
     */
    public static Observable<MainNewYorkTimesTopStories> streamFetchTopStories(String section) {
        NyTimesServices nyService = NyTimesServices.retrofitTopStories.create(NyTimesServices.class);
        return nyService.getNyTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * Most Popular
     * @param period Period
     * @return NyMostPopular period
     */
    public static Observable<MainNewYorkTimesTopStories> streamFetchMostPopular(int period) {
        NyTimesServices nyService = NyTimesServices.retrofitMostPopular.create(NyTimesServices.class);
        return nyService.getNyMostPopular(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    /**
     * Search and notification
     * @param beginDate Date of the begin
     * @param endDate End of the date
     * @param filter Filter
     * @param query Query
     * @param page Number of pages
     * @param sort Type of article
     * @param apiKey api key
     * @return NyTimes result
     */
    public static Observable<SearchApiResult> streamFetchSearch(String beginDate, String endDate, String filter, String query, int page, String sort, String apiKey) {
        NyTimesServices nyService = NyTimesServices.retrofitSearch.create(NyTimesServices.class);
        return nyService.getSearchArticle(beginDate, endDate, filter, page, query, sort, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
