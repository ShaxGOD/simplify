package com.example.simplify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<SetMovie> {
    List<SetMovie> setMovie;
    int resource;
    Context context;
    public CustomAdapter(Context context, int resource, List<SetMovie> setMovie) {
        super(context,resource,setMovie);
        this.context = context;
        this.resource = resource;
        this.setMovie = setMovie;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = layoutInflater.inflate(resource, null,false);
        TextView textViewTitle = view.findViewById(R.id.list_title);
        ImageView imageViewImage = view.findViewById(R.id.list_image);
        TextView textViewRating = view.findViewById(R.id.list_rating);
        TextView textViewCritic = view.findViewById(R.id.list_critic);
        TextView textViewDirected = view.findViewById(R.id.list_directed);
        final SetMovie setMovieNew = setMovie.get(position);
        textViewTitle.setText(setMovieNew.getTitle());
        textViewCritic.setText("Critics Consensus: " + setMovieNew.getCritic());
        textViewDirected.setText("Directed By: " +setMovieNew.getDirected());
        textViewRating.setText("Rating: #" +setMovieNew.getRating());
        Picasso.get().load(setMovieNew.getImage()).into(imageViewImage);
return view;
    }
}
