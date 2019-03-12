package com.gacon.julien.mynews.controllers.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.SearchResultFragment;

import butterknife.ButterKnife;

import static com.gacon.julien.mynews.controllers.activities.MainActivity.FARID;
import static com.gacon.julien.mynews.controllers.activities.MainActivity.ID_OTHERS_ACTIVITIES;

public class SearchActivity extends AppCompatActivity implements MainSearchFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        // Get Screen Id from MainActivity
        int screenId = getIntent().getIntExtra(ID_OTHERS_ACTIVITIES, 0);
        // Pass Screen Id to SearchFragment
        Bundle bundle = new Bundle();
        bundle.putInt("ScreenId", screenId);
        MainSearchFragment searchResultFragment = new MainSearchFragment();
        searchResultFragment.setArguments(bundle);
        Log.i("screenId", "Screen id is " + screenId);
    }

    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(), "Activity created !");

    }

}
