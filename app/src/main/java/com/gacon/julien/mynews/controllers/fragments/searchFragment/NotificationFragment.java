package com.gacon.julien.mynews.controllers.fragments.searchFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.activities.NotificationActivity;
import com.gacon.julien.mynews.controllers.activities.ResultActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseSearchAndNotifFragment implements View.OnClickListener {

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main_search;
    }

    @Override
    protected Intent getIntent() {
        return new Intent(getActivity(), NotificationActivity.class);
    }

    @Override
    protected boolean getNotificationVisibility() {
        return true;
    }

    @Override
    protected boolean getButtonVisibility() {
        return false;
    }

    public NotificationFragment() {
        // Required empty public constructor
    }

}
