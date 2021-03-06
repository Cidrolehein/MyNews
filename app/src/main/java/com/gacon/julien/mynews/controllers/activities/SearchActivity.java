package com.gacon.julien.mynews.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment;
import java.util.Objects;

/**
 * Search Activity Class for MainSearchFragment
 */
public class SearchActivity extends AppCompatActivity implements MainSearchFragment.OnButtonClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Configure Toolbar
        this.configureToolbar();
    }

    /**
     * Craet activity when button clicked
     * @param view new view
     */
    @Override
    public void onButtonClicked(View view) {
        Log.e(getClass().getSimpleName(), "Activity created !");
    }

    /**
     * Add toolbar
     */
    private void configureToolbar(){
        //Get the toolbar view inside the activity layout
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        // Enable the up button
        assert ab != null;
        Objects.requireNonNull(ab).setDisplayHomeAsUpEnabled(true);
    }
}
