package com.gacon.julien.mynews.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.models.Result;
import com.gacon.julien.mynews.views.datas.UpdateTextItems;

import butterknife.BindView;
import butterknife.ButterKnife;

class TopStoryApiViewHolder extends RecyclerView.ViewHolder {

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
    @BindView(R.id.first_layout)
    LinearLayout mLayout;

    UpdateTextItems mUpdate;

    // CONSTRUCTOR
    protected TopStoryApiViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void updateWithTopStoriesItems(final Result article, RequestManager glide) {

        mUpdate = new UpdateTextItems();

        this.textViewSection.setText(mUpdate.setSection(article));
        this.textViewSubSection.setText(mUpdate.setSubSection(article));
        this.textViewTitle.setText(mUpdate.setTitle(article));
        this.textViewDate.setText(mUpdate.setDate(article));
        this.setImage(article, glide);

    }

    private void setImage(Result article, RequestManager glide) {

        if (article.getMultimedia() != null) {
            if (article.getMultimedia().size() > 0) {
                glide.load(article.getMultimedia().get(0).getUrl()).apply(new RequestOptions().fallback(R.drawable.ic_launcher_background)).into(imageView);
            } else {
                getImageDefault(glide);
            }
        } else {
            if (article.getMedia().size() > 0) {
                glide.load(article.getMedia().get(0).getMediaMetadata().get(0).getUrl()).apply(new RequestOptions().fallback(R.drawable.ic_launcher_background)).into(imageView);
            } else {
                getImageDefault(glide);
            }
        }
    }

    private void getImageDefault(RequestManager glide) {
        glide.clear(imageView);
        imageView.setImageResource(R.drawable.ic_image_deffault);
    }

}
