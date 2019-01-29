package com.gacon.julien.mynews.Controllers.Fragments;

import com.gacon.julien.mynews.R;


public class MostPopularFragment extends BaseFragment {

    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_most_popular;}

}
