package com.gacon.julien.mynews;

import android.support.test.runner.AndroidJUnit4;

import com.gacon.julien.mynews.Controllers.Utils.NyTimesStreams;
import com.gacon.julien.mynews.Models.TopStories.MainNewYorkTimesTopStories;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MainFragmentTest {

    @Test
    public void downloadNyTopStories() throws Exception {
        // Get the stream
        Observable<MainNewYorkTimesTopStories> observableNyTopStories =
                NyTimesStreams.streamFetchTopStories("home");
        // Create a new TestObserver
        TestObserver<MainNewYorkTimesTopStories> testObserver = new TestObserver<>();
        // Launch observable
        observableNyTopStories.subscribeWith(testObserver)
                .assertNoErrors()// Check if no errors
                .assertNoTimeout() // Check if no Timeout
                .awaitTerminalEvent(); // Await the stream terminated before continue

        // Get list of articles results
        MainNewYorkTimesTopStories articleResultsDownload = testObserver.values().get(0);

        assertThat("Il y a un article qui s'est téléchargé", articleResultsDownload.getResults() != null);

    }
}
