package com.gacon.julien.mynews.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gacon.julien.mynews.views.adapters.viewPager.PageAdapter;
import com.gacon.julien.mynews.R;

import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.PREF;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SCREEN_KEY = "SCREEN_KEY";

    private SharedPreferences sharedPreferences;

    // For Design
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getBaseContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        mViewPager = findViewById(R.id.activity_main_viewpager);
        // Configure Toolbar
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        // View pager
        this.configureViewPagerAndTabs();
    }

    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        // 6 - Show fragment after user clicked on a menu item
        switch (id) {
            case R.id.activity_top_story:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.activity_most_popular:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.activity_arts:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.activity_search:
                launchNotificationsAndSearchActivity(2);
            case R.id.activity_notifications:
                launchNotificationsAndSearchActivity(1);
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // Configure Drawer Layout
    private void configureDrawerLayout() {
        this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureToolbar() {
        //Get the toolbar view inside the activity layout
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
    }

    // Configure NavigationView
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureViewPagerAndTabs() {
        // 1 - Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // 2 - Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });

        // 1 - Get TabLayout from layout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void launchNotificationsAndSearchActivity(int mFragId) {
        sharedPreferences
                .edit()
                .putInt(SCREEN_KEY, mFragId)
                .apply();

        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
