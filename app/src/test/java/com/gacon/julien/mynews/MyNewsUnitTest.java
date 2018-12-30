package com.gacon.julien.mynews;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class MyNewsUnitTest {

    @Test
    public void connect_url() {

        StringBuilder stringBuilder = new StringBuilder();
        
        String urlString = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        try {
            // 1 - Declare a URL Connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 2 - Open InputStream to connection
            conn.connect();
            InputStream in = conn.getInputStream();
            // 3 - Download and decode the string response
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (MalformedURLException exception){

        } catch (IOException exception) {

        } catch (Exception e){

        }
        
    }
}
