package com.gacon.julien.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.gacon.julien.mynews.Models.SearchArticleNewYorker;
import com.gacon.julien.mynews.R;

import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserViewHolder> {

    // 1 - Declaring a Glide object
    private RequestManager glide;

    // FOR DATA
    private List<SearchArticleNewYorker> githubUsers;

    // CONSTRUCTOR
    // 2 - Updating our constructor adding a Glide Object
    public GithubUserAdapter(List<SearchArticleNewYorker> githubUsers, RequestManager glide) {
        this.githubUsers = githubUsers;
        this.glide = glide;
    }

    @Override
    public GithubUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new GithubUserViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(GithubUserViewHolder viewHolder, int position) {
        // - 3 Passing the Glide object to each ViewHolder
        viewHolder.updateWithGithubUser(this.githubUsers.get(position), this.glide);
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.githubUsers.size();
    }
}
