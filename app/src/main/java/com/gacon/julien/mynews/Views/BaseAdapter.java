package com.gacon.julien.mynews.Views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gacon.julien.mynews.Models.TopStories.Result;
import com.gacon.julien.mynews.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseViewHolder> {

    // FOR DATA
    private List<Result> mNyTopStoriesList;
    private RequestManager glide;

    // CONSTRUCTOR
    public BaseAdapter(List<Result> mNyTopStoriesList, RequestManager glide) {
        this.mNyTopStoriesList = mNyTopStoriesList;
        this.glide = glide;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new BaseViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A TOPSTORIES
    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {

        viewHolder.updateWithTopStoriesItems(this.mNyTopStoriesList.get(position), this.glide);

    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mNyTopStoriesList.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        // FOR DESIGN
        @BindView(R.id.fragment_main_item_title)
        TextView textViewTitle;
        @BindView(R.id.fragment_main_section)
        TextView textViewSection;
        @BindView(R.id.fragment_main_subsection)
        TextView textViewSubSection;
        @BindView(R.id.fragment_main_date)
        TextView textViewDate;
        @BindView(R.id.fragment_main_item_image)
        ImageView imageView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void updateWithTopStoriesItems(Result article, RequestManager glide){

            // get article section / subsection / date / title / image
            if (!article.getSection().equals("")) {
                this.textViewSection.setText(article.getSection());
            }
            if (!article.getSubsection().equals("")) {
                this.textViewSubSection.setText(" > " + article.getSubsection());
            }
            if (!article.getTitle().equals("")) {
                this.textViewTitle.setText(article.getTitle());
            }
            if (!article.getPublishedDate().equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    String dateStr= article.getPublishedDate();
                    DateFormat srcDf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = srcDf.parse(dateStr);
                    DateFormat destDF = new SimpleDateFormat("dd/MM/yy");
                    dateStr = destDF.format(date);
                    this.textViewDate.setText(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (article.getMultimedia().size() > 0) {
                glide.load(article.getMultimedia().get(0).getUrl()).apply(new RequestOptions().fallback(R.drawable.ic_launcher_background)).into(imageView);
            } else {
                glide.clear(imageView);
                imageView.setImageResource(R.drawable.ic_image_deffault);
            }
        }
    }

}