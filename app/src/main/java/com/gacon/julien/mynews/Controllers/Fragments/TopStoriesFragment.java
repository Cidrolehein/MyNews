package com.gacon.julien.mynews.Controllers.Fragments;

import com.gacon.julien.mynews.R;

public class TopStoriesFragment extends BaseFragment {

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_top_stories;}

}