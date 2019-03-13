package com.gacon.julien.mynews.views.datas;

import com.gacon.julien.mynews.models.Result;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class setSubSectionTest {

    @Test
    public void setSubSection() {
        String sectionTitle = "TitleTest";
        String subsection = "SubsectionTest";
        String output;
        String expected = " > SubsectionTest";

        Result result = mock(Result.class);
        when(result.getSection()).thenReturn(sectionTitle);
        when(result.getSubsection()).thenReturn(subsection);
        UpdateTextItems updateTextItems = new UpdateTextItems();
        output = updateTextItems.setSubSection(result);
        assertEquals(expected, output);
    }
}