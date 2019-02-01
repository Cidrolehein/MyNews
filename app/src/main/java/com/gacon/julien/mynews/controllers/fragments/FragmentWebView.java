package com.gacon.julien.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gacon.julien.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWebView extends Fragment {


    public static FragmentWebView newInstance() {
        return (new FragmentWebView());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_web_view, container, false);
    }

}
