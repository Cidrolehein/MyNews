package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.activities.ResultActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainSearchFragment extends BaseSearchAndNotifFragment implements View.OnClickListener {

    public MainSearchFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main_search;
    }

    @Override
    protected boolean getNotificationVisibility() {
        return false;
    }

    @Override
    protected boolean getButtonVisibility() {
        return true;
    }

}
