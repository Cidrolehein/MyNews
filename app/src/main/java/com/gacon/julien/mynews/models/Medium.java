package com.gacon.julien.mynews.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medium {

    @SerializedName("media-metadata")
    @Expose
    private List<MediaMetadatum> mediaMetadata = null;

    public List<MediaMetadatum> getMediaMetadata() {
        return mediaMetadata;
    }

}
