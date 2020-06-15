package com.c.countdown_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     TextView timer  ;
    Button start_stop_button;
    SeekBar seekBar ;
    boolean start = false ;
    CountDownTimer countDownTimer  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("start1" ,start + " ");
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(300);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar = findViewById(R.id.seekBar);
                /*if(seekBar == null)
                    Toast.makeText(MainActivity.this , "null" ,Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this , " not null" ,Toast.LENGTH_LONG).show();*/
                updateTimer(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void updateTimer(long l )
    {

        int mins = (int) (l/60);
        int secs = (int) (l%60);
        timer = findViewById(R.id.timer);
        Log.i("Time" , mins + " "+ secs);
        timer.setText(String.format("%02d" , mins) + ":" + String.format("%02d" , secs));

    }
    //onclick button Method
    public void startTimer(View view)
    {
        // creating timers using CountDowns
        start_stop_button = findViewById(R.id.start_stop_button);
        timer = findViewById(R.id.timer);

        if(start == false) {
            seekBar.setVisibility(View.INVISIBLE);
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    Log.i("start with" , l + " ");
                    l = l / 1000 ;
                    updateTimer(l);
                    if(l==0)
                    {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.sound);
                        mediaPlayer.start();
                    }


                }

                @Override
                public void onFinish() {
                    start = false;
                    start_stop_button.setText("Start");
                    Toast.makeText(MainActivity.this , "Finished" , Toast.LENGTH_LONG).show();
                    seekBar.setVisibility(View.VISIBLE);

                }
            };
            Log.i("info" , "start = false");
            countDownTimer.start();
            start = true;
            start_stop_button.setText("Stop");
        }
        else{
            Log.i("info","start = true");
            start = false ;
            countDownTimer.cancel();
            start_stop_button.setText("Start");
            timer.setText("00:00");
            seekBar.setVisibility(View.VISIBLE);
        }

    }

}
