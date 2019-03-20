package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class MainSearchFragmentTest {

    MainSearchFragment mFragment;

    @Mock Context mContext;
    @Mock SharedPreferences mSharedPreferences;
    @Mock SharedPreferences.Editor mEditor;

    String query, filter, currentdate, endDate;

    @Before
    public void setUp() throws Exception {
        query = "Nature";
        filter = "Arts";
        currentdate = "01/12/2017";
        endDate = "05/12/2017";

        MockitoAnnotations.initMocks(this); // Creat all Mocks
        // Get Mock context.getSharedPreferences(String, int)
        doReturn(mSharedPreferences).when(mContext).getSharedPreferences(anyString(), anyInt());
        // Get Mock edit
        doReturn(mEditor).when(mSharedPreferences).edit();
        // return edit
        doReturn(mEditor).when(mEditor).putString(anyString(), anyString());

        // new fragment
        mFragment = new MainSearchFragment();
    }

    @Test
    public void testLoad() throws Exception {
        doReturn("Nature").when(mSharedPreferences).getString(anyString(), isNull(String.class));

    }

    @Test
    public void putSharedPrefTest() {
        mFragment.putDataToSharedPreferences(query, filter, currentdate, endDate);

        verify(mEditor, atLeastOnce()).putString(anyString(), eq("Nature"));
        verify(mEditor, atLeastOnce()).apply();
    }
}