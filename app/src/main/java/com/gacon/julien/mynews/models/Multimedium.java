package com.gacon.julien.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedium {
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

}
