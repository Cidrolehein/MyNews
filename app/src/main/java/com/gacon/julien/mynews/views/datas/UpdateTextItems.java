package com.gacon.julien.mynews.views.datas;
import com.gacon.julien.mynews.models.topStories.Result;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateTextItems {

    private String mString;

    private String Title;

    public String getTitle(Result aritcle, com.gacon.julien.mynews.models.mostPopular.Result articleMostPopular) {
        return Title;
    }

    public UpdateTextItems() { }

    public String setSection(Result article) {
        if (!article.getSection().equals("")) {
            mString = article.getSection();
        }
        return mString;
    }

    public String setSubSection(Result article) {
        String sectionTitle = article.getSection();
        String subsectionTitle = article.getSubsection();
        if (!article.getSubsection().equals("") && subsectionTitle.compareTo(sectionTitle) != 0) {
            mString = " > " + article.getSubsection();
        } else {
            mString = "";
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
