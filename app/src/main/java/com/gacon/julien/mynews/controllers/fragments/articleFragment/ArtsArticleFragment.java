package com.gacon.julien.mynews.controllers.fragments.articleFragment;


import android.support.v4.app.Fragment;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.MainNewYorkTimesTopStories;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass for Arts Article section
 */
public class ArtsArticleFragment extends BaseArticleFragment {

    // Instantiate fragment for page adaptor
    public static ArtsArticleFragment newInstance() {
        return (new ArtsArticleFragment());
    }

    /**
     *  Fragment layout
     * @return Layout
     */
    @Override
    protected int getFragmentLayout() {return R.layout.fragment_arts;}

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    /**
     * Create a stream request with Retrofit
     */
    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchTopStories("arts").subscribeWith(new DisposableObserver<MainNewYorkTimesTopStories>() {
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
