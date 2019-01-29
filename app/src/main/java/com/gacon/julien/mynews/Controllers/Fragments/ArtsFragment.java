package com.gacon.julien.mynews.Controllers.Fragments;


import android.support.v4.app.Fragment;

import com.gacon.julien.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtsFragment extends BaseFragment {


    public static ArtsFragment newInstance() {
        return (new ArtsFragment());
    }

    @Override
    protected int getFragmentLayout() {return R.layout.fragment_arts;}

}
