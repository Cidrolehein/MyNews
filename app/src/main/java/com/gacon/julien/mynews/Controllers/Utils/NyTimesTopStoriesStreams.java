package com.gacon.julien.mynews.Controllers.Utils;

import com.gacon.julien.mynews.Models.MostPopular.NyApiMostPopular;
import com.gacon.julien.mynews.Models.TopStories.MainNewYorkTimesTopStories;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NyTimesTopStoriesStreams {

    public static Observable<MainNewYorkTimesTopStories> streamFetchTopStories(String section) {
        NyTimesTopStoriesService nyService = NyTimesTopStoriesService.retrofitTopStories.create(NyTimesTopStoriesService.class);
        return nyService.getNyTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    public static Observable<NyApiMostPopular> streamFetchMostPopular(int period) {
        NyTimesTopStoriesService nyService = NyTimesTopStoriesService.retrofitMostPopular.create(NyTimesTopStoriesService.class);
        return nyService.getNyMostPopular(period)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
