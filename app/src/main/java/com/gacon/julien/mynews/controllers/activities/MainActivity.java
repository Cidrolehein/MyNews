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
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.PREF;

/**
 * Main Activity Class with Navigation View
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Shared Preferences for switch between Research and Notification fragment
    private SharedPreferences sharedPreferences;
    public static final String SCREEN_KEY = "SCREEN_KEY";

    // For Design
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    @BindView(R.id.activity_main_viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ButterKnif init
        ButterKnife.bind(this);
        // SharedPref init
        sharedPreferences = getBaseContext().getSharedPreferences(PREF, Context.MODE_PRIVATE);

        // Configure tools view

        // View pager with tabs
        this.configureViewPagerAndTabs();
        // Configure Toolbar
        this.configureToolbar();
        // DrawerLayout with Navigation View
        this.configureNavigationView();
        this.configureDrawerLayout();
    }

    // For ViewPager and Toolbar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
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
        // Show fragment after user clicked on a menu item
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

        return true;
    }

    // For DrawerLayout and NavigationDrawer
    @Override
    public void onBackPressed() {
        // Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     *     Configure Drawer Layout and NavigationView
      */
    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Configure NavigationView
     */
    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     *     Configure ViewPager, Tabs and ToolBar
     */
    private void configureViewPagerAndTabs() {
        // Get ViewPager from layout
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        // Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()) {
        });

        // Get TabLayout from layout
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        // Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        toolbar = findViewById(R.id.toolbar);
        // Set the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Configure and start tools items : Search and Notification Activity with FragId
     * @param mFragId Identification of fragment
     */
    private void launchNotificationsAndSearchActivity(int mFragId) {
        sharedPreferences
                .edit()
                .putInt(SCREEN_KEY, mFragId)
                .apply();

        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

}
