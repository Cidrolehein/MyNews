package com.gacon.julien.mynews.views.adapters.viewPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.gacon.julien.mynews.controllers.fragments.ArtsArticleFragment;
import com.gacon.julien.mynews.controllers.fragments.MostPopularArticleFragment;
import com.gacon.julien.mynews.controllers.fragments.TopStoriesArticleFragment;

public class PageAdapter extends FragmentPagerAdapter {

    //Default Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //Page number 1
                return TopStoriesArticleFragment.newInstance();
            case 1: //Page number 2
                return MostPopularArticleFragment.newInstance();
            case 2: //Page number 3
                return ArtsArticleFragment.newInstance();
            default:
                return null;
        }
    }

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

