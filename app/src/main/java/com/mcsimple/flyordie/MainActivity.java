package com.mcsimple.flyordie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView bird,enemy1,enemy2,enemy3,coin,volume;
    private Button buttonStart;
    private Animation animation;
    private MediaPlayer mediaPlayer;
    boolean volumeStat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bird = findViewById(R.id.bird);
        enemy1 = findViewById(R.id.enemy1);
        enemy2 = findViewById(R.id.enemy2);
        enemy3 = findViewById(R.id.enemy3);
        coin = findViewById(R.id.coin);
        volume = findViewById(R.id.volume);
        buttonStart = findViewById(R.id.buttonStart);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_animation);
        animation.setRepeatCount(Animation.INFINITE);
        bird.setAnimation(animation);
        enemy1.setAnimation(animation);
        enemy2.setAnimation(animation);
        enemy3.setAnimation(animation);
        coin.setAnimation(animation);


    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.hackman);
        mediaPlayer.start();

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!volumeStat){
                    mediaPlayer.setVolume(0,0);
                    volume.setImageResource(R.drawable.volume_off);
                    volumeStat = true;
                }
                else {
                    mediaPlayer.setVolume(1, 1);
                    volume.setImageResource(R.drawable.volume_on);
                    volumeStat = false;
                }
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.reset();
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);

            }
        });

    }
}