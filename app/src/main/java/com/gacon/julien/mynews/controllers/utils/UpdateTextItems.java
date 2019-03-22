package com.gacon.julien.mynews.controllers.utils;

import com.gacon.julien.mynews.models.Result;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utils class to manage models and JSon Strings
 */

public class UpdateTextItems {

    // text string tha we show on the app
    private String mString;

    // empty constructor
    public UpdateTextItems() { }

    // set article section
    public String setSection(Result article) {
        if (!article.getSection().equals("")) {
            mString = article.getSection();
        }
        return mString;
    }

    // set article SubSection - add ">" if they are unless get blank
    public String setSubSection(Result article) {
        String sectionTitle = article.getSection();
        mString = "";
        if (article.getSubsection() != null) {
            String subsectionTitle = article.getSubsection();
            if (!article.getSubsection().equals("") && subsectionTitle.compareTo(sectionTitle) != 0) {
                mString = " > " + article.getSubsection();
            }

        }
        return mString;
    }

    // set Title
    public String setTitle(Result article) {
        if (!article.getTitle().equals("")) {
            mString = article.getTitle();
        }
        return mString;
    }

    // set Date and convert to the format
    public String setDate(Result article){
        try {
            String dateStr= article.getPublishedDate();
            DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = srcDf.parse(dateStr);
            DateFormat destDF = new SimpleDateFormat("dd/MM/yy");
            dateStr = destDF.format(date);
            mString = dateStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mString;
    }

}
