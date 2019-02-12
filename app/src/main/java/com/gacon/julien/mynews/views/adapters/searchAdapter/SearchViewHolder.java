package com.gacon.julien.mynews.views.adapters.searchAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.models.Headline;
import com.gacon.julien.mynews.models.Result;
import com.gacon.julien.mynews.models.SearchApi;
import com.gacon.julien.mynews.views.datas.UpdateTextItems;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    // FOR DESIGN
    @BindView(R.id.fragment_main_item_title)
    TextView textViewTitle;

    UpdateTextItems mUpdate;

    // CONSTRUCTOR
    protected SearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void updateWithSearchResults(final Headline searchResult) {

        mUpdate = new UpdateTextItems();

        this.textViewTitle.setText(mUpdate.setSearchTitleSeo(searchResult));

    }

}
