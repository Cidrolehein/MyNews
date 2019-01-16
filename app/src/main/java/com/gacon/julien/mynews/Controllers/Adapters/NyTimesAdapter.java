package com.gacon.julien.mynews.Controllers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;
import com.gacon.julien.mynews.Models.Multimedium;
import com.gacon.julien.mynews.Models.Result;
import com.gacon.julien.mynews.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NyTimesAdapter extends RecyclerView.Adapter<NyTimesAdapter.NyTimesViewHolder> {

    // FOR DATA
    private List<Result> mNyTopStoriesList;
    private RequestManager glide;

    // FOR DESIGN
    @BindView(R.id.fragment_main_item_title)
    TextView textViewAsbtract;
    @BindView(R.id.fragment_main_item_image)
    ImageView imageView;

    // CONSTRUCTOR
    public NyTimesAdapter(List<Result> mNyTopStoriesList, RequestManager glide) {
        this.mNyTopStoriesList = mNyTopStoriesList;
        this.glide = glide;
    }

    @Override
    public NyTimesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new NyTimesViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A TOPSTORIES
    @Override
    public void onBindViewHolder(NyTimesViewHolder viewHolder, int position) {

        viewHolder.updateWithTopStoriesItems(this.mNyTopStoriesList.get(position), this.glide);

    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mNyTopStoriesList.size();
    }

    public class NyTimesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_main_item_title)
        TextView textViewAsbtract;
        @BindView(R.id.fragment_main_item_image)
        ImageView imageView;

        public NyTimesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void updateWithTopStoriesItems(Result article, RequestManager glide){
            this.textViewAsbtract.setText(article.getAbstract());
            if (article.getMultimedia().size() > 0) {
                glide.load(article.getMultimedia().get(0).getUrl()).into(imageView);
            }
        }
    }

}
