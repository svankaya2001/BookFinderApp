package com.example.bookfinderapp;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BookAdapter extends ArrayAdapter<Book> {
    private static class ViewHolder {
        public ImageView bookImage;
        public TextView bookTitle;
        public TextView bookAuthor;
        public TextView bookDesc;
        public ImageButton show, hide;
    }

    public BookAdapter(Context context, ArrayList<Book> aBooks) {
        super(context, 0, aBooks);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Book book = getItem(position);
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.book_items, parent, false);
            viewHolder.bookImage = (ImageView)convertView.findViewById(R.id.textImage);
            viewHolder.bookTitle = (TextView)convertView.findViewById(R.id.textTitle);
            viewHolder.bookAuthor = (TextView)convertView.findViewById(R.id.textAuthor);
            viewHolder.bookDesc = (TextView)convertView.findViewById(R.id.textDesc);
            viewHolder.show = (ImageButton)convertView.findViewById(R.id.show);
            viewHolder.hide  = (ImageButton)convertView.findViewById(R.id.hide);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.bookTitle.setText(book.getBookTitle());
        viewHolder.bookAuthor.setText(book.getBookAuthor());
        Glide.with(getContext()).load(book.getBookImage()).error(R.drawable.books).into(viewHolder.bookImage);
        //Log.i(TAG, "getView: "+book.getBookImage());
        viewHolder.bookDesc.setText(book.getBookDesc());
        viewHolder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.show.setVisibility(View.INVISIBLE);
                viewHolder.hide.setVisibility(View.VISIBLE);
                viewHolder.bookDesc.setMaxLines(Integer.MAX_VALUE);

            }
        });

        viewHolder.hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.show.setVisibility(View.VISIBLE);
                viewHolder.hide.setVisibility(View.INVISIBLE);
                viewHolder.bookDesc.setMaxLines(3);

            }
        });

        return convertView;
    }
}
