package com.gacon.julien.mynews.views.datas;

import com.gacon.julien.mynews.models.Result;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class test all methods that convert string of Json of good format.
 */

public class convertStringOfJson {

    private Result result = mock(Result.class);
    private String title = "This is a title";
    private String section = "This is a section";
    private String subsection = "SubsectionTest";
    private String dateInput = "2019-03-09 02:16:14";
    private String output;
    private String expected;

    // mock Result article model to getJson
    private void mockResultArticleClass() {
        when(result.getTitle()).thenReturn(title);
        when(result.getSection()).thenReturn(section);
        when(result.getSubsection()).thenReturn(subsection);
        when(result.getPublishedDate()).thenReturn(dateInput);
    }

    // Verify setTitle method - Title conversion
    @Test
    public void verifyTitle() {
        expected = "This is a title";
        UpdateTextItems updateTextItems = new UpdateTextItems();
        this.mockResultArticleClass();
        output = updateTextItems.setTitle(result);
        assertEquals(expected, output);
    }

    // Verify setSection method - Section conversion
    @Test
    public void verifySection() {
        expected = "This is a section";
        UpdateTextItems updateTextItems = new UpdateTextItems();
        this.mockResultArticleClass();
        output = updateTextItems.setSection(result);
        assertEquals(expected, output);
    }

    // Verify setSubSection method - SubSection conversion
    @Test
    public void verifySubSection() {
        expected = " > SubsectionTest";
        this.mockResultArticleClass();
        UpdateTextItems updateTextItems = new UpdateTextItems();
        output = updateTextItems.setSubSection(result);
        assertEquals(expected, output);
    }

    // Verifty setDate method - Date conversion
    @Test
    public void verifyDateConversion() {
        expected = "09/03/19";
        this.mockResultArticleClass();
        UpdateTextItems updateTextItems = new UpdateTextItems();
        output = updateTextItems.setDate(result);
        assertEquals(expected, output);
    }
}