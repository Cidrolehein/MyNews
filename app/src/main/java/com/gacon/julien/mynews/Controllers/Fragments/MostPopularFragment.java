package com.gacon.julien.mynews.Controllers.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gacon.julien.mynews.Controllers.ClasseUtils.GithubStreams;
import com.gacon.julien.mynews.Models.GithubUserInfo;
import com.gacon.julien.mynews.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class MostPopularFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.fragment_most_popular)
    TextView textView;

    //FOR DATA
    private Disposable disposable;

    public MostPopularFragment() { }
    public static MostPopularFragment newInstance() {
        return (new MostPopularFragment());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    // -----------------
    // ACTIONS
    // -----------------

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {this.executeSecondHttpRequestWithRetrofit(); }

    // ------------------------------
    //  HTTP REQUEST (RxJAVA)
    // ------------------------------

    // 1 - Execute our Stream
    private void executeSecondHttpRequestWithRetrofit(){
        // 1.1 - Update UI
        this.updateUIWhenStartingHTTPRequest();
        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = GithubStreams.streamFetchUserFollowingAndFetchFirstUserInfos("JakeWharton").subscribeWith(new DisposableObserver<GithubUserInfo>() {

            @Override
            public void onNext(GithubUserInfo users) {
                Log.e("TAG","On Next");
                updateUIWithUserInfo(users);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG","On Error"+Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("TAG","On Complete !!");
            }
        });
    }

    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
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

    private void updateUIWithUserInfo(GithubUserInfo userInfo){
        updateUIWhenStopingHTTPRequest("The first Following of Jake Wharthon is "+userInfo.getName()+" with "+userInfo.getFollowers()+" followers.");
    }

}
