package com.gacon.julien.mynews.controllers.fragments;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.mostPopular.NyApiMostPopular;
import com.gacon.julien.mynews.models.topStories.MainNewYorkTimesTopStories;

import io.reactivex.observers.DisposableObserver;


public class MostPopularFragment extends BaseFragment {

    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_most_popular;}

    @Override
    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchMostPopular(1).subscribeWith(new DisposableObserver<NyApiMostPopular>() {
            @Override
            public void onNext(NyApiMostPopular articles) {
                // Update RecyclerView after getting results from MostPopular API
                updateUIMostPopular(articles.getResults());
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
