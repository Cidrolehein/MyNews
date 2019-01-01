package com.gacon.julien.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gacon.julien.mynews.Controllers.ClasseUtils.ArticleNewYorkerCalls;
import com.gacon.julien.mynews.Controllers.ClasseUtils.NetworkAsyncTask;
import com.gacon.julien.mynews.Models.SearchArticleNewYorker;
import com.gacon.julien.mynews.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MostPopularFragment extends Fragment implements NetworkAsyncTask.Listeners, ArticleNewYorkerCalls.Callbacks {

    // FOR DESIGN
    @BindView(R.id.fragment_most_popular)
    TextView textView;

    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    // -----------------
    // ACTIONS
    // -----------------

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {this.executeHttpRequestWithRetrofit(); }

    // ------------------------------
    //  HTTP REQUEST (Retrofit Way)
    // ------------------------------

    // 4 - Execute HTTP request and update UI
    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
        ArticleNewYorkerCalls.fetchUserFollowing(this, "JakeWharton");
    }

    // 2 - Override callback methods

    @Override
    public void onResponse(@Nullable List<SearchArticleNewYorker> users) {

        // 2.1 - When getting response, we update UI
        if (users != null) this.updateUIWithListOfUsers(users);

    }

    @Override
    public void onFailure() {

        // 2.2 - When getting error, we update UI
        this.updateUIWhenStopingHTTPRequest("An error happened !");

    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void onPostExecute(String success) {

    }

    // ------------------
    //  UPDATE UI
    // ------------------

    private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }

    // 3 - Update UI showing only name of users
    private void updateUIWithListOfUsers(List<SearchArticleNewYorker> users){
        StringBuilder stringBuilder = new StringBuilder();
        for (SearchArticleNewYorker user : users){
            stringBuilder.append("-"+user.getLogin()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }

}
