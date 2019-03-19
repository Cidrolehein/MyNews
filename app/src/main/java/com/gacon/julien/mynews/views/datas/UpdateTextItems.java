package com.gacon.julien.mynews.views.datas;

import com.gacon.julien.mynews.models.Result;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTextItems {

    private String mString;

    public UpdateTextItems() { }

    public String setSection(Result article) {
        if (!article.getSection().equals("")) {
            mString = article.getSection();
        }
        return mString;
    }

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

    public String setTitle(Result article) {
        if (!article.getTitle().equals("")) {
            mString = article.getTitle();
        }
        return mString;
    }

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
