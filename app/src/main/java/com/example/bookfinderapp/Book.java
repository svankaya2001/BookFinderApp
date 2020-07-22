package com.example.bookfinderapp;

import android.text.TextUtils;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Book implements Serializable {
    private String bookAuthor;
    private String bookTitle;
    private String bookImage;
    private String bookDesc;
    private String previewLink;
    private String downloadLink;
    private String DownloadLinkAvailable;

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookImage(){
        return bookImage;
    }
    public String getBookDesc(){
        return bookDesc;
    }
    public String getPreviewLink(){
        return previewLink;
    }
    public String getDownloadLinkAvailable(){
        return DownloadLinkAvailable;
    }
    public String getDownloadLink(){
        return downloadLink;
    }

    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        try {
            JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");
            JSONObject accessInfo = jsonObject.getJSONObject("accessInfo");
            JSONObject imageLinkInfo = volumeInfo.getJSONObject("imageLinks");
            book.bookTitle = volumeInfo.getString("title");
            book.bookAuthor = getAuthor(volumeInfo);
            book.bookImage = imageLinkInfo.getString("thumbnail");
            book.bookDesc = volumeInfo.getString("description");
            book.previewLink = volumeInfo.getString("previewLink");
            book.DownloadLinkAvailable = accessInfo.getJSONObject("pdf").getString("isAvailable");
            //Log.i(TAG, "fromJson: "+ book.DownloadLinkAvailable);
            try {
                book.downloadLink = accessInfo.getJSONObject("pdf").getString("acsTokenLink");
            } catch (JSONException e) {
                e.printStackTrace();
                book.downloadLink = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    private static String getAuthor(final JSONObject jsonObject) {
        try {
            final JSONArray authors = jsonObject.getJSONArray("authors");
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }
    }

    public static ArrayList<Book> fromJson(JSONArray jsonArray) {
        ArrayList<Book> books = new ArrayList<Book>(10);
        for (int i = 0; i < 10; i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJson(bookJson);
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }
}
