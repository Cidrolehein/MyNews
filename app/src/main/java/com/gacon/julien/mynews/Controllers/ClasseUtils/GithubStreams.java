package com.gacon.julien.mynews.Controllers.ClasseUtils;

import com.gacon.julien.mynews.Models.GithubUserInfo;
import com.gacon.julien.mynews.Models.SearchArticleNewYorker;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.gacon.julien.mynews.Controllers.ClasseUtils.ArticleNewYorkerStreams.streamFetchUserFollowing;

public class GithubStreams {

    // 1 - Create a stream that will get user infos on Github API
    public static Observable<GithubUserInfo> streamFetchUserInfos(String username) {
        ArticleNewYorkerService gitHubService = ArticleNewYorkerService.retrofit.create(ArticleNewYorkerService.class);
        return gitHubService.getUserInfos(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    // 2 - Create a stream that will :
    //     A. Fetch all users followed by "username"
    //     B. Return the first user of the list
    //     C. Fetch details of the first user
    public static Observable<GithubUserInfo> streamFetchUserFollowingAndFetchFirstUserInfos(String username) {
        return streamFetchUserFollowing(username) // A.
                .map(new Function<List<SearchArticleNewYorker>, SearchArticleNewYorker>() {
                    @Override
                    public SearchArticleNewYorker apply(List<SearchArticleNewYorker> users) throws Exception {
                        return users.get(0); // B.
                    }
                })
                .flatMap(new Function<SearchArticleNewYorker, Observable<GithubUserInfo>>() {
                    @Override
                    public Observable<GithubUserInfo> apply(SearchArticleNewYorker user) throws Exception {
                        // C.
                        return streamFetchUserInfos(user.getLogin());
                    }
                });
    }
}
