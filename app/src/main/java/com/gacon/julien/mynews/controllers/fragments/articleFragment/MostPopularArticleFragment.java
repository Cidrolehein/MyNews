package com.gacon.julien.mynews.controllers.fragments.articleFragment;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;
import io.reactivex.observers.DisposableObserver;


public class MostPopularArticleFragment extends BaseArticleFragment {

    public static MostPopularArticleFragment newInstance() {
        return (new MostPopularArticleFragment());
    }

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_most_popular;}

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    @Override
    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchMostPopular(1).subscribeWith(new DisposableObserver<MainNewYorkTimesTopStories>() {
            @Override
            public void onNext(MainNewYorkTimesTopStories articles) {
                // Update RecyclerView after getting results from MostPopular API
                updateUI(articles.getResults());
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            @Override
            public void onComplete() { }
        });
    }

}