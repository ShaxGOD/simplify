package com.example.simplify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TimerForWork extends AppCompatActivity {
private TextView textOfCountDown;
private Button buttonStart;
private Spinner spinner;
private Button applyButton;
private CountDownTimer countDownTimer;
private long start_time_in_millis;
private long timeLeftMillis;
private boolean isTimerRunning;
private Button resetButton;
private long endTime;
private TextView spinnerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_for_work);
        spinner = findViewById(R.id.timer_spinner);
        textOfCountDown = findViewById(R.id.textview_timer);
        applyButton = findViewById(R.id.button_spinner);
        buttonStart = findViewById(R.id.button_start_timer);
        resetButton = findViewById(R.id.button_reset_timer);
        spinnerText = findViewById(R.id.spinner_text);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String input = spinner.getSelectedItem().toString();
            long millisInput = Long.parseLong(input) * 60000;
            setTime(millisInput);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTimerRunning) {
                    pauseTimer();
                }
                else {
                    startTimer();
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

    }
    public void startStop() {
        if(isTimerRunning) {
            stopTimer();
        }
        else {
            startTimer();
        }
    }
    private void setTime(long millis) {
        start_time_in_millis = millis;
        resetTimer();
    }
    public void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftMillis;
    countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
        @Override
        public void onTick(long l) {
            timeLeftMillis = l;
            updateTimer();
        }

        @Override
        public void onFinish() {
        isTimerRunning = false;
        updateTimerInterface();
        }
    }.start();
    isTimerRunning = true;
    updateTimerInterface();
    }
    public void stopTimer() {
    countDownTimer.cancel();
    isTimerRunning = false;
    }
    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        updateTimerInterface();
    }
private void resetTimer() {
        timeLeftMillis = start_time_in_millis;
        updateTimer();
        updateTimerInterface();
}
    public void updateTimer() {
        int mins = (int) timeLeftMillis/60000;
        int seconds = (int) timeLeftMillis%60000/1000;
        String timeLeft;
        timeLeft = "" + mins;
        timeLeft +=":";
        if(seconds < 10) timeLeft+="0";
        timeLeft += seconds;
        textOfCountDown.setText(timeLeft);

    }
    private void updateTimerInterface() {
        if(isTimerRunning) {
            applyButton.setVisibility(View.INVISIBLE);
            spinnerText.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.INVISIBLE);
            resetButton.setVisibility(View.INVISIBLE);
            buttonStart.setText("Pause");
        } else {
            applyButton.setVisibility(View.VISIBLE);
            spinnerText.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            buttonStart.setText("Start");
            if(timeLeftMillis < 1000) {
                buttonStart.setVisibility(View.INVISIBLE);
            } else {
                buttonStart.setVisibility(View.VISIBLE);
            }

            if(timeLeftMillis < start_time_in_millis) {
                resetButton.setVisibility(View.VISIBLE);
            }else {
                resetButton.setVisibility(View.INVISIBLE);
            }
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("start_time_in_millis", start_time_in_millis);
        editor.putLong("timeLeftMillis", timeLeftMillis);
        editor.putBoolean("isTimerRunning",isTimerRunning);
        editor.putLong("endTime", endTime);
        editor.apply();
        if(countDownTimer !=null) {
            countDownTimer.cancel();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        start_time_in_millis = prefs.getLong("start_time_in_millis", 600000);
        timeLeftMillis = prefs.getLong("timeLeftMillis", start_time_in_millis);
        isTimerRunning = prefs.getBoolean("isTimerRunning", false);
        updateTimer();
        updateTimerInterface();

        if(isTimerRunning) {
            endTime = prefs.getLong("endTime", 0);
            timeLeftMillis = endTime - System.currentTimeMillis();
            if(timeLeftMillis < 0) {
                timeLeftMillis = 0;
                isTimerRunning = false;
                updateTimerInterface();
                updateTimer();
            } else {
                startTimer();
            }
        }
    }
}