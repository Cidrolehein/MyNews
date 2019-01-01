package com.gacon.julien.mynews.Controllers.ClasseUtils;

import android.support.annotation.Nullable;

import com.gacon.julien.mynews.Models.SearchArticleNewYorker;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleNewYorkerCalls {

    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable List<SearchArticleNewYorker> users);
        void onFailure();
    }

    // 2 - Public method to start fetching users following by Jake Wharton
    public static void fetchUserFollowing(Callbacks callbacks, String username){

        // 2.1 - Create a weak reference to callback (avoid memory leaks)
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // 2.2 - Get a Retrofit instance and the related endpoints
        ArticleNewYorkerService articleNewYorkerService = ArticleNewYorkerService.retrofit.create(ArticleNewYorkerService.class);

        // 2.3 - Create the call on Github API
        Call<List<SearchArticleNewYorker>> call = articleNewYorkerService.getFollowing(username);
        // 2.4 - Start the call
        call.enqueue(new Callback<List<SearchArticleNewYorker>>() {

            @Override
            public void onResponse(Call<List<SearchArticleNewYorker>> call, Response<List<SearchArticleNewYorker>> response) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<SearchArticleNewYorker>> call, Throwable t) {
                // 2.5 - Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }
}
