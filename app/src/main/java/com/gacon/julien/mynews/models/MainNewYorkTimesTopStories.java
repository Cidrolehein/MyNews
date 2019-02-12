package com.gacon.julien.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainNewYorkTimesTopStories {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
    @SerializedName("snippet")
    @Expose
    private String snippet;

    public String getSnippet() {
        return snippet;
    }

}
