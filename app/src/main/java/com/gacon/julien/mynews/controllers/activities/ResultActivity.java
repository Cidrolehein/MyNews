package com.gacon.julien.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.SearchResultFragment;

public class ResultActivity extends AppCompatActivity {

    //Data
    private String mQuery;
    private String dateBeginForData;
    private String endDateForData;
    private String mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.getData();
        this.configureAndShowMainFragment();
    }

    // Data

    private void getData() {
        mQuery=getIntent().getStringExtra(MainSearchFragment.QUERY);
        dateBeginForData = getIntent().getStringExtra(MainSearchFragment.DATE_BEGIN);
        endDateForData = getIntent().getStringExtra(MainSearchFragment.END_DATE);
        mFilter = getIntent().getStringExtra(MainSearchFragment.FILTER);
    }

    // --------------
    // FRAGMENTS
    // --------------

    private void configureAndShowMainFragment() {
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        // 1 - Declare main fragment
        com.gacon.julien.mynews.controllers.fragments.searchFragment.SearchResultFragment searchResultFragment = (SearchResultFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_result);

        if (searchResultFragment == null) {
            // B - Create new main fragment
            searchResultFragment = new SearchResultFragment();
            // C - Add it to FrameLayout container and data
            Bundle queryData = new Bundle();
            queryData.putString("data", mQuery);
            searchResultFragment.setArguments(queryData);
            queryData.putString("dateBegin", dateBeginForData);
            searchResultFragment.setArguments(queryData);
            queryData.putString("endDate", endDateForData);
            searchResultFragment.setArguments(queryData);
            queryData.putString("filter", mFilter);
            searchResultFragment.setArguments(queryData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_result, searchResultFragment)
                    .commit();
        }
    }
}
