package com.mcsimple.flyordie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewResultInfo,textViewMyScore,textViewHighestScore;
    private Button buttonAgain;
    private int score;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResultInfo = findViewById(R.id.textViewResultInfo);
        textViewMyScore = findViewById(R.id.textViewMyScore);
        textViewHighestScore = findViewById(R.id.textViewHighestScore);
        buttonAgain = findViewById(R.id.buttonAgain);

        score = getIntent().getIntExtra("score",0);
        textViewMyScore.setText("Your score: " + score);

        sharedPreferences = this.getSharedPreferences("score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore",0);

        if (score >= 200){

            textViewResultInfo.setText("You won the game!");
            textViewHighestScore.setText("Highest Score: " + score);
            sharedPreferences.edit().putInt("highestScore",score).apply();
        } else if (score >= highestScore) {

            textViewResultInfo.setText("Try once again!");
            textViewHighestScore.setText("Highest Score: " + score);
            sharedPreferences.edit().putInt("highestScore",score).apply();
        } else {
            textViewResultInfo.setText("Try once again!");
            textViewHighestScore.setText("Highest Score: " + highestScore);
        }

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Fly or Die");
        builder.setMessage("Are you shure you want to quite?");
        builder.setCancelable(false);
        builder.setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }
}