package com.gacon.julien.mynews.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.gacon.julien.mynews.views.adapters.viewPager.PageAdapter;
import com.gacon.julien.mynews.R;

import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.PREF;

public class MainActivity extends AppCompatActivity {

    public static final String ID_OTHERS_ACTIVITIES = "Details_and_help_activity";
    public static final int FARID = 30;
    public static final String SCREEN_ID = "SCREEN_ID";
    public static final String SCREEN_KEY = "SCREEN_KEY";

    private Context mContext;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getBaseContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        //1 - Configure Toolbar
        this.configureToolbar();
        // - View pager
        this.configureViewPagerAndTabs();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                launchNotificationsAndSearchActivity(1);
                return true;
            case R.id.menu_activity_main_search:
                launchNotificationsAndSearchActivity(2);
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar(){
        //Get the toolbar view inside the activity layout
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureViewPagerAndTabs(){
        // 1 - Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // 2 - Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });

        // 1 - Get TabLayout from layout
        TabLayout tabs= findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void launchNotificationsAndSearchActivity(int mFragId)
    {
        sharedPreferences
                .edit()
                .putInt(SCREEN_KEY, mFragId)
                .apply();

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(ID_OTHERS_ACTIVITIES, mFragId);
        setResult(RESULT_OK, intent);
        startActivityForResult(intent, FARID);
    }
}
