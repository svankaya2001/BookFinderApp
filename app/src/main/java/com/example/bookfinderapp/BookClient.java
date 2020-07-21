package com.example.bookfinderapp;

import android.util.Log;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;




public class BookClient {
    private static final String base_url = "https://www.googleapis.com/books/v1/volumes?";
    private static final String query_param = "q=";
    private AsyncHttpClient client;

    public BookClient() {

        this.client = new AsyncHttpClient();
    }

    public void getBooks(final String query, JsonHttpResponseHandler handler) {
        try {
            String url = base_url +query_param;
            client.get(url + URLEncoder.encode(query, "utf-8"), handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }




}
