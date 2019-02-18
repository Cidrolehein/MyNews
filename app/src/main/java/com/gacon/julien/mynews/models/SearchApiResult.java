package com.gacon.julien.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchApiResult {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public class Response {

        @SerializedName("docs")
        @Expose
        private List<Result> docs = null;

        public List<Result> getDocs() {
            return docs;
        }
    }

}
