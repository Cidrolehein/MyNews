package com.gacon.julien.mynews.Controllers.Utils;

import com.gacon.julien.mynews.Models.SearchArticleNewYorker;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ArticleNewYorkerStreams {

    public static Observable<List<SearchArticleNewYorker>> streamFetchUserFollowing(String username){
        ArticleNewYorkerService gitHubService = ArticleNewYorkerService.retrofit.create(ArticleNewYorkerService.class);
        return gitHubService.getFollowing(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}
