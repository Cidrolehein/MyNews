package com.gacon.julien.mynews.controllers.fragments;


import android.support.v4.app.Fragment;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.topStories.MainNewYorkTimesTopStories;

import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtsFragment extends BaseFragment {


    public static ArtsFragment newInstance() {
        return (new ArtsFragment());
    }

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_arts;}

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchTopStories("arts").subscribeWith(new DisposableObserver<MainNewYorkTimesTopStories>() {
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
