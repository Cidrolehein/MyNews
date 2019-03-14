package com.gacon.julien.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Result {

    @SerializedName(value = "section", alternate = "section_name")
    @Expose
    private String section;
    @SerializedName(value = "subsection", alternate = "subsectoinName")
    @Expose
    private String subsection;
    @SerializedName(value = "title", alternate = "snippet")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName(value = "url", alternate = "web_url")
    @Expose
    private String url;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName(value = "published_date", alternate = "pub_date")
    @Expose
    private String publishedDate;
    @SerializedName(value = "multimedia")
    @Expose
    private List<Multimedium> multimedia = null;
    @SerializedName("media")
    @Expose
    private List<Medium> media = null;

    public List<Medium> getMedia() {
        return media;
    }

    public String getSection() {
        return section;
    }

    public String getSubsection() {
        return subsection;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

}
