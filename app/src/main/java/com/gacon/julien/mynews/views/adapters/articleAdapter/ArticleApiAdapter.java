package com.gacon.julien.mynews.views.adapters.articleAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.models.Result;
import java.util.List;

public class ArticleApiAdapter extends RecyclerView.Adapter<ArticleApiViewHolder> {

    private List<Result> mNyTopStoriesList;
    private RequestManager glide;

    // CONSTRUCTOR
    public ArticleApiAdapter(List<Result> mNyTopStoriesList, RequestManager glide) {
        this.mNyTopStoriesList = mNyTopStoriesList;
        this.glide = glide;
    }

    @NonNull
    @Override
    public ArticleApiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new ArticleApiViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A TOPSTORIES
    @Override
    public void onBindViewHolder(@NonNull ArticleApiViewHolder viewHolder, int position) {

        viewHolder.updateWithTopStoriesItems(this.mNyTopStoriesList.get(position), this.glide);

    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mNyTopStoriesList.size();
    }

    public String getURL (int position) {
        return mNyTopStoriesList.get(position).getUrl();
    }

}