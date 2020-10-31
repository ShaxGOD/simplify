package com.example.simplify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToeGame extends AppCompatActivity implements View.OnClickListener {
    private Button [] []  buttons = new Button[3][3];
    private boolean turnOfFirstPlayer = true;
    private int roundCount;
    private int firstPlayerPoints;
    private int secondPlayerPoints;
    private TextView textViewFirstPlayer;
    private TextView textViewSecondPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);
        textViewFirstPlayer  = findViewById(R.id.textview_playerfirst);
        textViewSecondPlayer = findViewById(R.id.textview_playersecond);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resourceID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonRestart = findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if( ! ((Button) view).getText().toString().equals("")) {
            return;
        }
        if(turnOfFirstPlayer) {
            ((Button) view).setText("X");
        }
        else {
            ((Button) view).setText("0");
        }

        roundCount++;

        if(checkForWin()) {
            if(turnOfFirstPlayer) {
                firstPlayerWins();
            }
            else {
                secondPlayerWins();
            }
        } else if (roundCount == 9) {
            draw();
        }
        else {
            turnOfFirstPlayer = !turnOfFirstPlayer;
        }

    }
    private Boolean checkForWin() {
        String [][] field = new String[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                field[i][j] =  buttons[i][j].getText().toString();
            }
        }
        for(int i = 0; i < 3; i++) {
            if(field[i][0].equals(field[i][1])
            && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return  true;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return  true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return  true;
        }
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return  true;
        }
        return false;
    }
    private void firstPlayerWins () {
firstPlayerPoints++;
        Toast.makeText(this, "First player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        restartBoard();
    }
    private void secondPlayerWins() {
        secondPlayerPoints++;
        Toast.makeText(this, "Second player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        restartBoard();
    }
    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        restartBoard();
    }
    private void updatePointsText() {
    textViewFirstPlayer.setText("First player: " + firstPlayerPoints);
    textViewSecondPlayer.setText("Second player: " + secondPlayerPoints);
    }
    private void restartBoard() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        turnOfFirstPlayer = true;
    }
    private void restartGame() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
        updatePointsText();;
        restartBoard();
    }

    @Override
    protected  void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundCount",roundCount);
        outState.putInt("firstPlayerPoints",firstPlayerPoints);
        outState.putInt("secondPlayerPoints", secondPlayerPoints);
        outState.putBoolean("turnOfFirstPlayer", turnOfFirstPlayer);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundCount = savedInstanceState.getInt("roundCount");
        firstPlayerPoints = savedInstanceState.getInt("firstPlayerPoints");
        secondPlayerPoints = savedInstanceState.getInt("secondPlayerPoints");
        turnOfFirstPlayer = savedInstanceState.getBoolean("turnOfFirstPlayer");
    }

}