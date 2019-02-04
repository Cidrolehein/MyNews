package com.gacon.julien.mynews.controllers.fragments;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;

import io.reactivex.observers.DisposableObserver;

public class TopStoriesFragment extends BaseFragment {

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
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
                System.out.println(e);
                e.printStackTrace();
            }
            @Override
            public void onComplete() { }
        });
    }

}