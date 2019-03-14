package com.gacon.julien.mynews.controllers.fragments.articleFragment;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;

import io.reactivex.observers.DisposableObserver;

public class TopStoriesArticleFragment extends BaseArticleFragment {

    public static TopStoriesArticleFragment newInstance() {
        return (new TopStoriesArticleFragment());
    }

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_top_stories;}

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchTopStories("home").subscribeWith(new DisposableObserver<MainNewYorkTimesTopStories>() {
            @Override
            public void onNext(MainNewYorkTimesTopStories articles) {
                // Update RecyclerView after getting results from NYTimes Top Stories API
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