package com.gacon.julien.mynews.Controllers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gacon.julien.mynews.Models.MainNewYorkTimesTopStories;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.Views.NyTimesViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NyTimesAdapter extends RecyclerView.Adapter<NyTimesViewHolder> {

    // FOR DATA
    private MainNewYorkTimesTopStories mNyTopStories;

    // CONSTRUCTOR
    public NyTimesAdapter(MainNewYorkTimesTopStories mNyTopStories) {
        this.mNyTopStories = mNyTopStories;
    }

    @Override
    public NyTimesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new NyTimesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A GITHUBUSER
    @Override
    public void onBindViewHolder(NyTimesViewHolder viewHolder, int position) {
        viewHolder.updateWithNyUser(this.mNyTopStories.get(position));
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mNyTopStories.size();
    }
}
