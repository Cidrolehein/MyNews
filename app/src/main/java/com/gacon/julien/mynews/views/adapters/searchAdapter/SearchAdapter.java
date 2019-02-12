package com.gacon.julien.mynews.views.adapters.searchAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.models.Headline;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<Headline> mSearchList;

    // CONSTRUCTOR
    public SearchAdapter(List<Headline> searchList) {
        this.mSearchList = searchList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new SearchViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A TOPSTORIES
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder viewHolder, int position) {

        viewHolder.updateWithSearchResults(this.mSearchList.get(position));

    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mSearchList.size();
    }

}
