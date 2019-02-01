package com.gacon.julien.mynews.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gacon.julien.mynews.models.mostPopular.Result;
import com.gacon.julien.mynews.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder> {

    // FOR DATA
    private List<Result> mostPopularList;
    private RequestManager glide;

    // CONSTRUCTOR
    public MostPopularAdapter(List<Result> mostPopularList, RequestManager glide) {
        this.mostPopularList = mostPopularList;
        this.glide = glide;
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false);

        return new MostPopularViewHolder(view);
    }

    // UPDATE VIEW HOLDER WITH A MOST POPULAR LIST
    @Override
    public void onBindViewHolder(MostPopularViewHolder viewHolder, int position) {

        viewHolder.updateWithMostPopularItems(this.mostPopularList.get(position), this.glide);

    }

    // RETURN THE TOTAL COUNT OF ITEMS IN THE LIST
    @Override
    public int getItemCount() {
        return this.mostPopularList.size();
    }

    public class MostPopularViewHolder extends RecyclerView.ViewHolder {

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

        public MostPopularViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void updateWithMostPopularItems(Result article, RequestManager glide){

            // get article section / subsection / date / title / image
            if (!article.getSection().equals("")) {
                this.textViewSection.setText(article.getSection());
            }
            if (!article.getTitle().equals("")) {
                this.textViewTitle.setText(article.getTitle());
            }
            if (!article.getPublishedDate().equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
            if (article.getMedia().size() > 0) {
                glide.load(article.getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(new RequestOptions().fallback(R.drawable.ic_launcher_background)).into(imageView);
            } else {
                glide.clear(imageView);
                imageView.setImageResource(R.drawable.ic_image_deffault);
            }
        }
    }

}
