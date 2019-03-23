package com.gacon.julien.mynews.controllers.activities;

import android.app.Activity;
import android.app.TabActivity;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.gacon.julien.mynews.R;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Objects;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Some testing in toolbar of MainActivity
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private MainActivity mMainActivity;

    /**
     * Creating Rule for MainActivity Testing
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Instantiate MainActivity
     */
    @Before
    public void setUp() {
        mMainActivity = mActivityRule.getActivity();
        assertThat(mMainActivity, notNullValue());
    }

    /**
     * Test if Toolbar is availabale
     */
    @Test
    public void checkTabLayoutDisplayed() {
        onView(withId(R.id.toolbar))
                .perform(click())
                .check(matches(isDisplayed()));
    }

    /**
     * Test of fragment title
     */
    @Test
    @UiThreadTest
    public void fragmentTitleTest() {
        assertThat(Objects.requireNonNull(mMainActivity.mViewPager.getAdapter()).getPageTitle(2), Matchers.<CharSequence>equalTo("ARTS"));
    }

}