package com.gacon.julien.mynews.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.SearchResultFragment;

public class ResultActivity extends AppCompatActivity {

    // 1 - Declare main fragment
    private SearchResultFragment SearchResultFragment;

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
        SearchResultFragment = (SearchResultFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_result);

        if (SearchResultFragment == null) {
            // B - Create new main fragment
            SearchResultFragment = new SearchResultFragment();
            // C - Add it to FrameLayout container and data
            Bundle queryData = new Bundle();
            queryData.putString("data", mQuery);
            SearchResultFragment.setArguments(queryData);
            queryData.putString("dateBegin", dateBeginForData);
            SearchResultFragment.setArguments(queryData);
            queryData.putString("endDate", endDateForData);
            SearchResultFragment.setArguments(queryData);
            queryData.putString("filter", mFilter);
            SearchResultFragment.setArguments(queryData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_result, SearchResultFragment)
                    .commit();
        }
    }
}
