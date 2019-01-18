package com.gacon.julien.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.gacon.julien.mynews.Controllers.Utils.NyTimesTopStoriesStreams;
import com.gacon.julien.mynews.Models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.Models.Result;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.Views.NyTimesAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class TopStoriesFragment extends Fragment {

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    //FOR DATA
    private Disposable disposable;
    // 2 - Declare list of TopStories (List<Result>) & Adapter
    private List<Result> mResultList;
    private NyTimesAdapter adapter;

    public TopStoriesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.executeHttpRequestWithRetrofit();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // 3 - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView(){
        // 3.1 - Reset list
        this.mResultList = new ArrayList<>();
        // 3.2 - Create adapter passing the list of NY top stories
        adapter = new NyTimesAdapter(mResultList, Glide.with(this));
        // 3.3 - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // 3.4 - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private void executeHttpRequestWithRetrofit() {
        this.disposable = NyTimesTopStoriesStreams.streamFetchTopStories("home").subscribeWith(new DisposableObserver<MainNewYorkTimesTopStories>() {
            @Override
            public void onNext(MainNewYorkTimesTopStories topStories) {

                // Update RecyclerView after getting results from NYTimes Top Stories API
                updateUI(topStories.getResults());
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }

        });

    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private void updateUI(List<Result> topStories) {
        mResultList.addAll(topStories);
        adapter.notifyDataSetChanged();
    }

}