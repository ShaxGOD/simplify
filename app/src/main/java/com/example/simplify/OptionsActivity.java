package com.example.simplify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {
CardView movieCard;
CardView speechCard;
CardView gameCard;
CardView timerCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        movieCard =  findViewById(R.id.movieCard);
        speechCard = findViewById(R.id.speechCard);
        gameCard = findViewById(R.id.gameCard);
        timerCard = findViewById(R.id.timerCard);

        movieCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this,TopMovies.class));
            }
        });

        speechCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this,Speech.class));
            }
        });

        gameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this,TicTacToeGame.class));
            }
        });

        timerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionsActivity.this,TimerForWork.class));
            }
        });

    }
}