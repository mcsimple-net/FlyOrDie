package com.mcsimple.flyordie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private ImageView bird,enemy1,enemy2,enemy3,coin1,coin2,right1,right2,right3,gameVolume;
    private TextView textViewScore,textViewStartInfo;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    private boolean beginControl = false;
    private MediaPlayer gameMedia;
    boolean vol = false;
    private Runnable runnable,runnable2;
    private Handler handler,handler2;

    //Positions
    int birdX,enemy1X,enemy2X,enemy3X,coin1X,coin2X;
    int birdY,enemy1Y,enemy2Y,enemy3Y,coin1Y,coin2Y;

    //Screen dimensions
    int screenWidth;
    int screenHeight;

    int right = 3;
    int score = 0;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        bird = findViewById(R.id.imageViewBird);
        enemy1 = findViewById(R.id.imageViewEnemy1);
        enemy2 = findViewById(R.id.imageViewEnemy2);
        enemy3 = findViewById(R.id.imageViewEnemy3);
        coin1 = findViewById(R.id.imageViewCoin1);
        coin2 = findViewById(R.id.imageViewCoin2);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        textViewScore = findViewById(R.id.textViewScore);
        textViewStartInfo = findViewById(R.id.textViewStartInfo);
        constraintLayout = findViewById(R.id.constraintLayout);
        gameVolume = findViewById(R.id.gameVolume);


        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                textViewStartInfo.setVisibility(View.INVISIBLE);

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    touchControl = true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    touchControl = false;
                }

                if (!beginControl){
                    beginControl = true;

                    screenWidth = (int)constraintLayout.getWidth();
                    screenHeight = (int)constraintLayout.getHeight();

                    birdX = (int)bird.getX();
                    birdY = (int)bird.getY();


                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {

                            birdMove();
                            enemyControl();
                            collisionControl();


                        }
                    };
                    handler.post(runnable);
                }
                else {

                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        touchControl = true;
                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                        touchControl = false;
                    }

                }



                return true;
            }
        });


    }
    public void birdMove(){

        if (touchControl){
            birdY = birdY - (screenHeight/50);
        }
        else {
            birdY = birdY + (screenHeight/50);
        }

        if (birdY <= bird.getHeight()){
            birdY = bird.getHeight();
        }
        if (birdY >= (screenHeight - bird.getHeight())){
            birdY = (screenHeight - bird.getHeight());
        }



        bird.setY(birdY);
    }

    public void enemyControl(){

        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        enemy1X = enemy1X - (screenWidth/150);

        if (score >=50 && score < 100){
            enemy1X = enemy1X - (screenWidth/130);
        }
        if (score >=100 && score < 150){
            enemy1X = enemy1X - (screenWidth/120);
        }
        if (score > 150){
            enemy1X = enemy1X - (screenWidth/100);
        }

        if (enemy1X < 0){
            enemy1X = screenWidth +200;
            enemy1Y = (int)Math.floor(Math.random() * screenHeight);

            if (enemy1Y <= bird.getHeight()){
                enemy1Y = bird.getHeight();
            }
            if (enemy1Y >= (screenHeight - enemy1.getHeight())){
                enemy1Y = (screenHeight - enemy1.getHeight());
            }
        }
        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);

        enemy2X = enemy2X - (screenWidth/140);

        if (score >=50 && score < 100){
            enemy2X = enemy2X - (screenWidth/120);
        }
        if (score >=100 && score < 150){
            enemy2X = enemy2X - (screenWidth/110);
        }
        if (score > 150){
            enemy2X = enemy2X - (screenWidth/90);
        }

        if (enemy1X < 0){
            enemy1X = screenWidth +200;
            enemy1Y = (int)Math.floor(Math.random() * screenHeight);

            if (enemy2Y <= bird.getHeight()){
                enemy2Y = bird.getHeight();
            }
            if (enemy2Y >= (screenHeight - enemy2.getHeight())){
                enemy2Y = (screenHeight - enemy2.getHeight());
            }
        }
        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);

        enemy3X = enemy3X - (screenWidth/130);

        if (score >=50 && score < 100){
            enemy3X = enemy3X - (screenWidth/110);
        }
        if (score >=100 && score < 150){
            enemy3X = enemy3X - (screenWidth/100);
        }
        if (score > 150){
            enemy3X = enemy3X - (screenWidth/80);
        }

        if (enemy3X < 0){
            enemy3X = screenWidth +200;
            enemy3Y = (int)Math.floor(Math.random() * screenHeight);

            if (enemy3Y <= bird.getHeight()){
                enemy3Y = bird.getHeight();
            }
            if (enemy3Y >= (screenHeight - enemy3.getHeight())){
                enemy3Y = (screenHeight - enemy3.getHeight());
            }
        }
        enemy3.setX(enemy3X);
        enemy3.setY(enemy3Y);

        coin1X = coin1X - (screenWidth/120);
        if (coin1X < 0){
            coin1X = screenWidth +200;
            coin1Y = (int)Math.floor(Math.random() * screenHeight);

            if (coin1Y <= bird.getHeight()){
                coin1Y = bird.getHeight();
            }
            if (coin1Y >= (screenHeight - coin1.getHeight())){
                coin1Y = (screenHeight - coin1.getHeight());
            }
        }
        coin1.setX(coin1X);
        coin1.setY(coin1Y);

        coin2X = coin2X - (screenWidth/110);
        if (coin2X < 0){
            coin2X = screenWidth +200;
            coin2Y = (int)Math.floor(Math.random() * screenHeight);

            if (coin2Y <= bird.getHeight()){
                coin2Y = bird.getHeight();
            }
            if (coin2Y >= (screenHeight - coin2.getHeight())){
                coin2Y = (screenHeight - coin2.getHeight());
            }
        }
        coin2.setX(coin2X);
        coin2.setY(coin2Y);

    }

    @SuppressLint("SetTextI18n")
    public void collisionControl(){
        int centerEnemy1X = enemy1X + enemy1.getWidth() / 2;
        int centerEnemy1Y = enemy1Y + enemy1.getHeight() / 2;

        if (centerEnemy1X >= birdX
            && centerEnemy1X <= (birdX + bird.getWidth())
            && centerEnemy1Y >= birdY
            && centerEnemy1Y <= (birdY + bird.getHeight()))
        {
            enemy1X = screenWidth + 200;
            right--;
        }

        int centerEnemy2X = enemy2X + enemy2.getWidth() / 2;
        int centerEnemy2Y = enemy2Y + enemy2.getHeight() / 2;

        if (centerEnemy1X >= birdX
                && centerEnemy2X <= (birdX + bird.getWidth())
                && centerEnemy2Y >= birdY
                && centerEnemy2Y <= (birdY + bird.getHeight()))
        {
            enemy2X = screenWidth + 200;
            right--;
        }

        int centerEnemy3X = enemy3X + enemy3.getWidth() / 2;
        int centerEnemy3Y = enemy3Y + enemy3.getHeight() / 2;

        if (centerEnemy3X >= birdX
                && centerEnemy3X <= (birdX + bird.getWidth())
                && centerEnemy3Y >= birdY
                && centerEnemy3Y <= (birdY + bird.getHeight()))
        {
            enemy3X = screenWidth + 200;
            right--;
        }

        int centerCoin1X = coin1X + coin1.getWidth() / 2;
        int centerCoin1Y = coin1Y + coin1.getHeight() / 2;

        if (centerCoin1X >= birdX
                && centerCoin1X <= (birdX + bird.getWidth())
                && centerCoin1Y >= birdY
                && centerCoin1Y <= (birdY + bird.getHeight()))
        {
            coin1X = screenWidth + 200;
            score = score + 10;
            textViewScore.setText("" + score);
        }

        int centerCoin2X = coin2X + coin2.getWidth() / 2;
        int centerCoin2Y = coin2Y + coin2.getHeight() / 2;

        if (centerCoin2X >= birdX
                && centerCoin2X <= (birdX + bird.getWidth())
                && centerCoin2Y >= birdY
                && centerCoin2Y <= (birdY + bird.getHeight()))
        {
            coin2X = screenWidth + 200;
            score = score + 10;
            textViewScore.setText("" + score);
        }

        if (right >= 0 && score < 200)
        {
            if (right == 2)
            {
                right1.setImageResource(R.drawable.grey_heart);
            }
            if (right == 1)
            {
                right2.setImageResource(R.drawable.grey_heart);
            }
            if (right == 0)
            {
                right3.setImageResource(R.drawable.grey_heart);
            }
            handler.postDelayed(runnable,20);
        }
        else if (score >= 1000)
        {
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            textViewStartInfo.setVisibility(View.VISIBLE);
            textViewStartInfo.setText("Congratulations! You won the game!");
            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);


            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {

                    birdX = birdX + (screenWidth / 300);
                    bird.setX(birdX);
                    bird.setY(screenHeight / 2f);

                    if (birdX <= screenWidth){
                        handler2.postDelayed(runnable2,20);
                    }
                    else {
                        handler2.removeCallbacks(runnable2);

                        Intent intent = new Intent(GameActivity.this,ResultActivity.class);
                        intent.putExtra("score",score);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            handler2.post(runnable2);
        }
        else {
            gameMedia.reset();
            handler.removeCallbacks(runnable);
            Intent intent = new Intent(GameActivity.this,ResultActivity.class);
            intent.putExtra("score",score);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        gameMedia = MediaPlayer.create(getApplicationContext(), R.raw.hackman);
        gameMedia.start();

        gameVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!vol) {
                    gameMedia.setVolume(0, 0);
                    gameVolume.setImageResource(R.drawable.volume_off);
                    vol = true;
                } else {
                    gameMedia.setVolume(1, 1);
                    gameVolume.setImageResource(R.drawable.volume_on);
                    vol = false;
                }
            }
        });
    }
}