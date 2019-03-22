package com.gacon.julien.mynews.controllers.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;
import com.gacon.julien.mynews.R;
import com.gacon.julien.mynews.models.SearchApiResult;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.DATE_BEGIN;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.END_DATE;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.FILTER;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.PREF;
import static com.gacon.julien.mynews.controllers.fragments.searchFragment.MainSearchFragment.QUERY;
import static com.gacon.julien.mynews.controllers.utils.AppNotification.CHANNEL_ID;

/**
 * Alarm Service
 */

public class MyAlarmService extends Service {

    // size of list
    private int size;
    // for data
    private String query, dateBegin, endDate, filter;
    SharedPreferences mSharedPreferences;
    // for retrofit
    private Disposable disposable;

    @Override
    public void onCreate(){
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // show message in the app when alarm starting
        Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
        // connect to the NyTimes api
        executeHttpRequest();
        Log.i("LocalService", "Received start id " + startId + ": " + intent);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
        return null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
        return super.onUnbind(intent);
    }

    // Connect to the NyTimes API
    protected void executeHttpRequest() {
        mSharedPreferences = getApplicationContext().getSharedPreferences(PREF, MODE_PRIVATE);
        // load data from search fragment
        loadSharedPreferences();
        // retrofit
        this.disposable = NyTimesStreams.streamFetchSearch(dateBegin,endDate,filter, query, 30, "newest", "KzYIpjPOMj98klY5cukvyxBmBhzKwDKO").subscribeWith(new DisposableObserver<SearchApiResult>() {
            @Override
            public void onNext(SearchApiResult articles) {
                // Update RecyclerView after getting results from SearchApiResult API
                size = articles.getResponse().getDocs().size();
                if (size > 0) {
                    // Notifications
                    createNotification(size + " nouveaux articles sont en ligne !");
                    // restart date begin
                    Calendar currentTime = Calendar.getInstance();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
                    String currentdate = df.format(currentTime.getTime());
                    mSharedPreferences
                            .edit()
                            .putString(DATE_BEGIN, currentdate)
                            .apply();
                } else {
                    createNotification("Aucun article trouvé.");
                }
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
            @Override
            public void onComplete() { }
        });
    }

    // load data from search fragment
    public void loadSharedPreferences() {
        query = mSharedPreferences.getString(QUERY, null);
        dateBegin = mSharedPreferences.getString(DATE_BEGIN, null);
        endDate = mSharedPreferences.getString(END_DATE, null);
        filter = mSharedPreferences.getString(FILTER, null);
    }

    // get the notification
    private void createNotification(String texte) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                //icon
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                //title
                .setContentTitle("Articles New York Times")
                //text
                .setContentText(texte)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(texte))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }
}
