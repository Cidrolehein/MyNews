package com.gacon.julien.mynews.Controllers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gacon.julien.mynews.Models.Result;
import com.gacon.julien.mynews.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NyTimesAdapter extends RecyclerView.Adapter<NyTimesAdapter.NyTimesViewHolder> {

    // FOR DATA
    private List<Result> mNyTopStoriesList;

    // CONSTRUCTOR
    public NyTimesAdapter(List<Result> mNyTopStoriesList) {
        this.mNyTopStoriesList = mNyTopStoriesList;
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

        viewHolder.textViewAsbtract.setText(mNyTopStoriesList.get(position).getAbstract());
    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mNyTopStoriesList.size();
    }

    public class NyTimesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_main_item_title)
        TextView textViewAsbtract;

        public NyTimesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
