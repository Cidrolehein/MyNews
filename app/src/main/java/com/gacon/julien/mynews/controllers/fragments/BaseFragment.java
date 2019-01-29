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
import com.bumptech.glide.Glide;
import com.gacon.julien.mynews.controllers.utils.NyTimesStreams;
import com.gacon.julien.mynews.models.mostPopular.NyApiMostPopular;
import com.gacon.julien.mynews.models.topStories.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.models.topStories.Result;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.views.ArtsAdapter;
import com.gacon.julien.mynews.views.MostPopularAdapter;
import com.gacon.julien.mynews.views.NyTimesAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class BaseFragment extends Fragment {

    protected abstract int getFragmentLayout();

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    // Declare the SwipeRefreshLayout
    @BindView(R.id.fragment_main_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    //FOR DATA
    private Disposable disposable;
    // 2 - Declare lists & Adapters
    private List<Result> mResultList;
    private List<com.gacon.julien.mynews.models.mostPopular.Result> mostPopularResult;
    private NyTimesAdapter adapter;
    private MostPopularAdapter mostPopularAdapter;
    private ArtsAdapter artsAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.executeHttpRequest();
        this.configureSwipeRefreshLayout();

        return view;
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
        switch (getFragmentLayout()){
            case R.layout.fragment_top_stories:
                this.mResultList = new ArrayList<>();
                adapter = new NyTimesAdapter(mResultList, Glide.with(this));
                this.recyclerView.setAdapter(this.adapter);
            break;
            case R.layout.fragment_most_popular:
                this.mostPopularResult = new ArrayList<>();
                mostPopularAdapter = new MostPopularAdapter(mostPopularResult, Glide.with(this));
                this.recyclerView.setAdapter(this.mostPopularAdapter);
            break;
            case R.layout.fragment_arts:
                this.mResultList = new ArrayList<>();
                artsAdapter = new ArtsAdapter(mResultList, Glide.with(this));
                this.recyclerView.setAdapter(this.artsAdapter);
                break;
            default:
                break;
        }

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

    private void executeHttpRequest() {

        switch (getFragmentLayout()){
            case R.layout.fragment_top_stories:
               this.executeHttpRequestTopStory();
                break;
            case R.layout.fragment_most_popular:
                this.executeHttpRequestMostPopular();
                break;
            case R.layout.fragment_arts:
                this.executeHttpRequestArts();
            default:
                break;
        }

    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<Result> textArticle) {
        //stop refreshing and clear actual list of text article
        swipeRefreshLayout.setRefreshing(false);
        mResultList.clear();
        mResultList.addAll(textArticle);
        adapter.notifyDataSetChanged();
    }

    private void updateUIMostPopular(List<com.gacon.julien.mynews.models.mostPopular.Result> textArticle) {
        //stop refreshing and clear actual list of text article
        swipeRefreshLayout.setRefreshing(false);
        mostPopularResult.clear();
        mostPopularResult.addAll(textArticle);
        mostPopularAdapter.notifyDataSetChanged();
    }

    private void updateUIArts(List<Result> textArticle) {
        //stop refreshing and clear actual list of text article
        swipeRefreshLayout.setRefreshing(false);
        mResultList.clear();
        mResultList.addAll(textArticle);
        artsAdapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // Streams Request
    // with Retrofit
    // -------------------

    private void executeHttpRequestTopStory() {
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

    private void executeHttpRequestMostPopular() {
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

    private void executeHttpRequestArts() {
        this.disposable = NyTimesStreams.streamFetchTopStories("arts").subscribeWith(new DisposableObserver<MainNewYorkTimesTopStories>() {
            @Override
            public void onNext(MainNewYorkTimesTopStories articles) {
                // Update RecyclerView after getting results from NYTimes Top Stories API
                updateUIArts(articles.getResults());
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
