package com.example.simplify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.util.Locale;

public class Speech extends AppCompatActivity {
private TextToSpeech textToSpeech;
private EditText editText;
private SeekBar seekBarPitch;
private SeekBar seekBarSpeed;
private Button speakButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);
speakButton = findViewById(R.id.pronounce);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS) {
                   int result =  textToSpeech.setLanguage(Locale.ENGLISH);
                   if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                       Log.e("TextToSpeech","Language not supported");
                   }
                   else{
                       speakButton.setEnabled(true);
                   }
                }
                else {
                    Log.e("TextToSpeech","Initialization failed");
                }
            }
        });

        editText = findViewById(R.id.enterText);
        seekBarPitch = findViewById(R.id.pitch);
        seekBarSpeed = findViewById(R.id.speed);

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }
    private void speak() {
        String text = editText.getText().toString();
        float pitch = (float) seekBarPitch.getProgress() / 50;
        if(pitch < 0.1) pitch = 0.1f;
        float speed = (float) seekBarSpeed.getProgress() / 50;
        if(speed < 0.1) speed = 0.1f;

        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech !=null) {
            textToSpeech.stop();
            textToSpeech.shutdown();;
        }

    }
}