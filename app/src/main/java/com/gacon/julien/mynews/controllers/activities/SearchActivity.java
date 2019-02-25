package com.gacon.julien.mynews.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment;

import butterknife.ButterKnife;

import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.QUERY;

public class SearchActivity extends AppCompatActivity implements MainSearchFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

    }

    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(), "Activity created !");
    }
}
