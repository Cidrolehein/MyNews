package com.gacon.julien.mynews.views.datas;
import com.gacon.julien.mynews.models.topStories.Result;

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
        if (!article.getSubsection().equals("")) {
            mString = " > " + article.getSubsection();
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
