package com.gacon.julien.mynews.controllers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.gacon.julien.mynews.controllers.activities.WebViewActivity;
import com.gacon.julien.mynews.controllers.utils.ItemClickSupport;
import com.gacon.julien.mynews.models.Result;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.views.adapters.TopStoryApiAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public abstract class BaseArticleFragment extends Fragment {

    protected abstract int getFragmentLayout();
    protected abstract void executeHttpRequest();
    public static final String BUNDLE_URL = "BUNDLE_URL";

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    protected Disposable disposable;
    // 2 - Declare lists & Adapters
    private List<Result> mResultList;
    private TopStoryApiAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.executeHttpRequest();
        this.configureSwipeRefreshLayout();
        // 2 - Calling the method that configuring click on RecyclerView
        this.configureOnClickRecyclerView();

        return view;
    }


    // -----------------
    // ACTION
    // -----------------

    // 1 - Configure item click on RecyclerView

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.fragment_main_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        String article;

                        // 1 - Get user from adapter
                        article = adapter.getURL(position);
                        // 2 - Show result in a new WebView
                        Intent intent = new Intent(getActivity(), WebViewActivity.class);
                        intent.putExtra(BUNDLE_URL, article);
                        startActivity(intent);
                    }

                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // CONFIGURATION
    // ON CREATE VIEW
    // METHODS
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){

        this.mResultList = new ArrayList<>();
        adapter = new TopStoryApiAdapter(mResultList, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);

        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    // Configure the SwipeRefreshLayout
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

    protected void updateUI(List<Result> textArticle) {
        //stop refreshing and clear actual list of text article
        swipeRefreshLayout.setRefreshing(false);
        mResultList.clear();
        mResultList.addAll(textArticle);
        adapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

}


