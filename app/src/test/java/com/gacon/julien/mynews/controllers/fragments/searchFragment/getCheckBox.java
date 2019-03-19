package com.gacon.julien.mynews.controllers.fragments.searchFragment;

import android.widget.CheckBox;
import android.widget.TimePicker;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class getCheckBox {

    @Test
    public void getCheckBox() {
        String Filter = "";
        CheckBox Arts = mock(CheckBox.class);
        CheckBox Business = mock(CheckBox.class);
        CheckBox Entrepreneurs = mock(CheckBox.class);
        CheckBox Politics = mock(CheckBox.class);
        CheckBox Sports = mock(CheckBox.class);
        CheckBox Travel = mock(CheckBox.class);

        Mockito.when(Arts.isChecked()).thenReturn(Boolean.valueOf("Arts"));
        Mockito.when(Business.isChecked()).thenReturn(Boolean.valueOf("Business"));



    }
}