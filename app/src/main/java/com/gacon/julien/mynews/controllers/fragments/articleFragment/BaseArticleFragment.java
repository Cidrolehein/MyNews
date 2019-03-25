package com.gacon.julien.mynews.controllers.fragments.articleFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.gacon.julien.mynews.controllers.activities.WebViewActivity;
import com.gacon.julien.mynews.controllers.utils.ItemClickSupport;
import com.gacon.julien.mynews.models.Result;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.views.adapters.articleAdapter.ArticleApiAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

/**
 * Class to manage fragments
 */
public abstract class BaseArticleFragment extends Fragment {

    protected abstract int getFragmentLayout();
    //Connect to the NyTimes Api
    protected abstract void executeHttpRequest();
    public static final String BUNDLE_URL = "BUNDLE_URL";

    // ------------- FOR DESIGN --------------------------

    // Declare the RecyclerView
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    // ------------- FOR DATA --------------------------
    // Declare disposable to call Retrofit
    protected Disposable disposable;
    // Declare lists & Adapters
    private List<Result> mResultList;
    private ArticleApiAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(getFragmentLayout(), container, false);
        // Instentiate ButterKnife
        ButterKnife.bind(this, view);
        // RecyclerView
        this.configureRecyclerView();
        // Connect to the Api
        this.executeHttpRequest();
        // Calling the method that configuring click on RecyclerView
        this.configureOnClickRecyclerView();
        // SwipeRefreshLayout
        this.configureSwipeRefreshLayout();

        return view;
    }

    /**
     * When the fragment is distroy
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTION
    // -----------------

    /**
     * Configure item click on RecyclerView
     */
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        // 1 - Get user from adapter
                        String article = adapter.getURL(position);
                        // 2 - Show result in a new WebView
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(BUNDLE_URL, article);
                        startActivity(intent);
                    }

                });
    }

    // -----------------
    // CONFIGURATION
    // ON CREATE VIEW
    // METHODS
    // -----------------

    /**
     * Configure RecyclerView, Adapter, LayoutManager & glue it together
     */
    private void configureRecyclerView(){

        this.mResultList = new ArrayList<>();
        adapter = new ArticleApiAdapter(mResultList, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);

        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    /**
     * Configure the SwipeRefreshLayout
     */
    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequest();
            }
        });
    }

    // -------------------
    // UPDATE UI
    // -------------------

    /**
     * stop refreshing and clear actual list of text article
     * @param textArticle List of article
     */
    protected void updateUI(List<Result> textArticle) {
        swipeRefreshLayout.setRefreshing(false);
        mResultList.clear();
        sortDateArray(textArticle);
        mResultList.addAll(textArticle);
        // reverse the list to have an ascendant list of date in RecyclerView
        Collections.reverse(mResultList);
        Log.i("List reverse", "List finale" + mResultList);
        adapter.notifyDataSetChanged();
    }

    /**
     * Method to sort and orgenize a list of dates
     * @param textArticle Dates of published articles
     */
    private void sortDateArray(final List<Result> textArticle) {
        for (int i = 0; i < textArticle.size(); i++)
        Collections.sort(textArticle, new Comparator<Result>() {
            public int compare(Result textArticle1, Result textArticle2) {
                String date1 = (String)textArticle1.getPublishedDate();
                String date2 = (String)textArticle2.getPublishedDate();
                int result = date1.compareTo(date2);
                if(result == 0){
                    return date1.compareTo(date2);
                }
                return result;
            }
        });
        Iterator<Result> it = textArticle.iterator();
        while (it.hasNext())
            Log.i("List", "List finale" + it.next());
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

}


