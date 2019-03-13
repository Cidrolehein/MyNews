package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.BaseArticleFragment;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.SearchApiResult;
import io.reactivex.observers.DisposableObserver;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultFragment extends BaseArticleFragment {

    private int size;

    public static SearchResultFragment newInstance() {
        return (new SearchResultFragment());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_search_result;
    }

    @Override
    protected void executeHttpRequest() {

        assert getArguments() != null;
        String query = getArguments().getString("data");
        String dateBegin = getArguments().getString("dateBegin");
        String endDate = getArguments().getString("endDate");
        String filter = getArguments().getString("filter");

        this.disposable = NyTimesStreams.streamFetchSearch(dateBegin,endDate,filter, query, 30, "newest", "KzYIpjPOMj98klY5cukvyxBmBhzKwDKO").subscribeWith(new DisposableObserver<SearchApiResult>() {
            @Override
            public void onNext(SearchApiResult articles) {
                // Update RecyclerView after getting results from SearchApiResult API
                updateUI(articles.getResponse().getDocs());

                size = articles.getResponse().getDocs().size();
                if (size == 0) {
                    Toast toast = Toast.makeText(getContext(), "Aucun résultat trouvé", Toast.LENGTH_LONG);
                    toast.show();
                }
                System.out.println(size);
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
