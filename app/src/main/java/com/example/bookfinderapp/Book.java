package com.example.bookfinderapp;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Book {
    private String textTitle;
    private String textAuthor;
    private String imageurl;

    public String getTextTitle(){
        return textTitle;
    }
    public String getTextAuthor(){
        return textAuthor;
    }
    public String getImageurl(){
        return imageurl;
    }
    public static Book fromJSON(JSONObject jsonObject){
        Book book = new Book();
        try {
            JSONObject volumeinfo = jsonObject.getJSONObject("volumeInfo");

            book.textTitle = volumeinfo.getString("title");
            book.textAuthor = getAuthor(volumeinfo);
            book.imageurl = volumeinfo.getJSONObject("imageLinks").getString("thumbnail");

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    private static String getAuthor(JSONObject volumeinfo) {
        try {
            final JSONArray authors = volumeinfo.getJSONArray("authors");
            int numauthors = authors.length();
            final String[] authors_string = new String[numauthors];
            for(int i =0; i<numauthors;i++){
                authors_string[i] = authors.getString(i);
            }
            return TextUtils.join(",", authors_string);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

    }
    public static ArrayList<Book> fromJSON(JSONArray jsonArray){
        ArrayList<Book> books = new ArrayList<Book>(jsonArray.length());

        for(int i =0; i<jsonArray.length(); i++){
            JSONObject bookJSON = null;
            try{
                bookJSON = jsonArray.getJSONObject(i);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Book book = fromJSON(bookJSON);
            if(book!=null)books.add(book);
        }
        return books;

    }
}
