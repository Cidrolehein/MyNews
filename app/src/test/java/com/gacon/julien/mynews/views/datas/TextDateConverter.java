package com.gacon.julien.mynews.views.datas;

import com.gacon.julien.mynews.models.Result;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TextDateConverter {

    // test date converter
    @Test
    public void setDate() {
        String input = "2019-03-09 02:16:14";
        String output;
        String expected = "09/03/19";

        Result result = mock(Result.class);
        when(result.getPublishedDate()).thenReturn(input);
        UpdateTextItems updateTextItems = new UpdateTextItems();
        output = updateTextItems.setDate(result);
        assertEquals(expected, output);
    }
}