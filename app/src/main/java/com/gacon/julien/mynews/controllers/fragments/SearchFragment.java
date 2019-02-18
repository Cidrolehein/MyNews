package com.gacon.julien.mynews.controllers.fragments;

import android.support.v4.app.Fragment;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.models.SearchApi;
import com.gacon.julien.mynews.models.SearchApiResult;

import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseArticleFragment {

    public static SearchFragment newInstance() {
        return (new SearchFragment());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchSearch().subscribeWith(new DisposableObserver<SearchApiResult>() {
            @Override
            public void onNext(SearchApiResult articles) {
                // Update RecyclerView after getting results from SearchApiResult API
                updateUI(articles.getResponse().getDocs());
            }
            @Override
            public void onError(Throwable e) {
                System.out.println(e);
                e.printStackTrace();
            }
            @Override
            public void onComplete() { }
        });
    }
}
