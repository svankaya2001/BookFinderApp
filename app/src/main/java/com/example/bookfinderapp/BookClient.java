package com.example.bookfinderapp;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BookClient {
    private static final String base_url = "https://www.googleapis.com/books/v1/volumes?";
    private static final String query_param = "q=";
    private static final String max_results = "maxResults";
    private static final String print_type = "printType";
    private AsyncHttpClient client;

    private String getBase_url(String url){
        return base_url + url;
    }
    private void getBooks(final String query, JsonHttpResponseHandler jsonHttpResponseHandler){
        try {
            String url = getBase_url(query_param);
            String complete_url = url + URLEncoder.encode(query, "utf-8") + "&" +max_results + "=10&" + print_type + "=books";
            client.get(complete_url, jsonHttpResponseHandler);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}
