package com.gacon.julien.mynews.views.adapters.viewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.ArtsArticleFragment;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.MostPopularArticleFragment;
import com.gacon.julien.mynews.controllers.fragments.searchFragment.SearchResultFragment;
import com.gacon.julien.mynews.controllers.fragments.articleFragment.TopStoriesArticleFragment;

/**
 * Simple Page Adaptor to manage fragments
 */
public class PageAdapter extends FragmentPagerAdapter {

    /**
     * Constructor
     * @param mgr Fragment Manager
     */
    protected PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    /**
     * Number of fragment to show
     * @return Number of fragment
     */
    @Override
    public int getCount() {
        return(3);
    }

    /**
     * Items
     * @param position Item position
     * @return New item instance
     */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return TopStoriesArticleFragment.newInstance();
            case 1: //Page number 2
                return MostPopularArticleFragment.newInstance();
            case 2: //Page number 3
                return ArtsArticleFragment.newInstance();
            case 3: // Create a new instance for Search / Notification fragment
                return SearchResultFragment.newInstance();
            default:
                return null;
        }
    }

    /**
     * Title of the pages
     * @param position Item position
     * @return Title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: //Page number 1
                return "TOP STORIES";
            case 1: //Page number 2
                return "MOST POPULAR";
            case 2: //Page number 3
                return "ARTS";
            default:
                return null;
        }
    }
}

