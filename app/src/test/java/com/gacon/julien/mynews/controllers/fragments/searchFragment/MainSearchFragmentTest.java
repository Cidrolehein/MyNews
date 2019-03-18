package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.PREF;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MainSearchFragmentTest {

    private static final String PREFS = "PREFS";
    private static final String SCREEN_KEYS = "SCREEN_KEYS";
    private SharedPreferences mSharedPreferences;

    @Before
    public void before() {
        Context context = mock(Context.class);
        mSharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    @Test
    public void putAndGetPreference() throws Exception {
        int input = 1;

        mSharedPreferences.edit().putInt(SCREEN_KEYS, input).apply();
        int ouput = mSharedPreferences.getInt(PREF, 0);

        // Verify thate the received data is correct
        assertEquals(input, ouput);
    }

    @After
    public void after() {
        mSharedPreferences.edit().putInt(SCREEN_KEYS, 0).apply();
    }

}