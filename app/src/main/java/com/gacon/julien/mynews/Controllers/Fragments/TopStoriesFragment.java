package com.gacon.julien.mynews.Controllers.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.gacon.julien.mynews.Controllers.Adapters.NyTimesAdapter;
import com.gacon.julien.mynews.Controllers.Utils.NyTimesTopStoriesService;
import com.gacon.julien.mynews.Models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.Models.Multimedium;
import com.gacon.julien.mynews.Models.Result;
import com.gacon.julien.mynews.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesFragment extends Fragment {

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    // FOR DESIGN
    @BindView(R.id.fragment_main_recycler_view)
    RecyclerView recyclerView; // 1 - Declare RecyclerView

    //FOR DATA
    private Disposable disposable;
    // 2 - Declare list of TopStories (MainTopStories) & Adapter
    private List<MainNewYorkTimesTopStories> mNyTopStoriesList;
    private NyTimesAdapter adapter;

    public TopStoriesFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this, view);

        /*Create handle for the RetrofitInstance interface*/
        NyTimesTopStoriesService service = NyTimesTopStoriesService.retrofit.create(NyTimesTopStoriesService.class);

        /*Call the method with parameter in the interface to get the employee data*/
        Call<MainNewYorkTimesTopStories> call = service.getNyTopStories("home");

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<MainNewYorkTimesTopStories>() {
            @Override
            public void onResponse(Call<MainNewYorkTimesTopStories> call, Response<MainNewYorkTimesTopStories> response) {
                generateTopStoriesList(response.body().getResults());
            }

            @Override
            public void onFailure(Call<MainNewYorkTimesTopStories> call, Throwable e) {
                Log.e("TAG", "On Error" + Log.getStackTraceString(e));
            }
        });

        return view;
    }

    /*Method to generate List of Top Stories using RecyclerView with custom adapter*/
    private void generateTopStoriesList(List<Result> empDataList) {

        adapter = new NyTimesAdapter(empDataList, Glide.with(this));

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(adapter);

    }

}
