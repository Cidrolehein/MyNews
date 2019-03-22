package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.BaseArticleFragment;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.SearchApiResult;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass for search result
 */
public class SearchResultFragment extends BaseArticleFragment {

    // size of Api Respons
    private int size;
    // Date
    private String dateBegin;
    private String endDate;
    // Filter of ChekBox
    private String filter;
    // Query of EditText
    private String query;

    // New Instance
    public static SearchResultFragment newInstance() {
        return (new SearchResultFragment());
    }

    /**
     *  Fragment layout
     * @return Layout
     */
    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search_result;
    }

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    /**
     * Create a stream request with Retrofit
     */
    @Override
    protected void executeHttpRequest() {

        // Data
        getData();

        this.disposable = NyTimesStreams.streamFetchSearch(dateBegin, endDate, filter, query, 30, "newest", "KzYIpjPOMj98klY5cukvyxBmBhzKwDKO").subscribeWith(new DisposableObserver<SearchApiResult>() {
            @Override
            public void onNext(SearchApiResult articles) {
                // Update RecyclerView after getting results from SearchApiResult API
                updateUI(articles.getResponse().getDocs());

                size = articles.getResponse().getDocs().size();
                if (size == 0) {
                    Toast toast = Toast.makeText(getContext(), "Aucun résultat trouvé", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * Get data from MainSearchFragment
     */
    private void getData() {
        assert getArguments() != null;
        // get query and filter
        query = getArguments().getString("data");
        filter = getArguments().getString("filter");
        // get date
        dateBegin = getArguments().getString("dateBegin");
        endDate = getArguments().getString("endDate");
    }
}
