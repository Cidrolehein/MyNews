package com.gacon.julien.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.Headline;
import com.gacon.julien.mynews.views.adapters.searchAdapter.SearchAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    protected Disposable disposable;
    // 2 - Declare lists & Adapters
    private List mResultList;
    private SearchAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
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
        adapter = new SearchAdapter(mResultList);
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

    protected void updateUI(Headline results) {
        mResultList.addAll(results);
        adapter.notifyDataSetChanged();

    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    protected void executeHttpRequest() {
        this.disposable = NyTimesStreams.streamFetchSearch("", "").subscribeWith(new DisposableObserver<Headline>() {
            @Override
            public void onNext(Headline results) {
                // Update RecyclerView after getting results from Search API
                updateUI(results);
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
