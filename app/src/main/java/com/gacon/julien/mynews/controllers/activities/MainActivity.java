package com.gacon.julien.mynews.controllers.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.gacon.julien.mynews.views.adapters.viewPager.PageAdapter;
import com.gacon.julien.mynews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Intent intent = new Intent(getApplication(), NotificationActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_activity_main_search:
                intent = new Intent(getApplication(), SearchActivity.class);
                startActivity(intent);
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
}
