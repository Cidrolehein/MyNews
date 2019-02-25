package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.support.v4.app.Fragment;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.BaseArticleFragment;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.SearchApiResult;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends BaseArticleFragment {

    public static SearchResultFragment newInstance() {
        return (new SearchResultFragment());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void executeHttpRequest() {

        String query = getArguments().getString("data");
        String dateBegin = getArguments().getString("dateBegin");
        String endDate = getArguments().getString("endDate");
        String filter = getArguments().getString("filter");
        String art = "art";

        this.disposable = NyTimesStreams.streamFetchSearch(dateBegin,endDate,filter, query, "KzYIpjPOMj98klY5cukvyxBmBhzKwDKO").subscribeWith(new DisposableObserver<SearchApiResult>() {
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
