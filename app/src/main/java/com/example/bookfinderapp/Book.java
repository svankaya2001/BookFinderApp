package com.example.bookfinderapp;

import android.text.TextUtils;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {
    private String bookAuthor;
    private String bookTitle;
    private String bookImage;
    private String bookDesc;

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

    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        try {
            JSONObject volumeInfo = jsonObject.getJSONObject("volumeInfo");
            JSONObject imageLinkInfo = volumeInfo.getJSONObject("imageLinks");
            book.bookTitle = volumeInfo.getString("title");
            book.bookAuthor = getAuthor(volumeInfo);
            book.bookImage = imageLinkInfo.getString("thumbnail");

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
