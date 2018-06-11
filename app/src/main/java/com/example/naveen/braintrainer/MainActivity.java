package com.example.naveen.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.util.Log;

import java.util.Random;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ConstraintLayout gameLayout;
    TextView timeTextView;
    CountDownTimer cdt;
    Button playAgainButton;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    String answer;
    int answerInt,rand1,rand2,answerIndex,value,total,score,someNumber;
    Random r,b;
    Button firstOption,secondOption,thirdOption,fourthOption,answerButton;
    Button[] all;
    ArrayList<Integer> options = new ArrayList<Integer>();

    public void startGame(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        cdt.start();
    }
    public void playAgain(View view){
        scoreTextView.setText("0/0");
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        cdt.start();
        score = 0;
        total = 0;
        options.clear();
        setSum();
    }

    public void checkResult(View view){
        if( view.getTag().toString().equals(Integer.toString(answerIndex))){
            resultTextView.setText("Correct!");
            score = score + 1;
        }
        else{
            resultTextView.setText("Wrong!");
        }
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(total));
        total = total + 1;
        options.clear();
        setSum();
        resultTextView.setVisibility(View.VISIBLE);
    }

    public void setSum(){
        rand1 = r.nextInt(100);
        rand2 = r.nextInt(100);
        answerIndex = b.nextInt(4);
        answerInt = rand1 + rand2;
        answer = Integer.toString(rand1 + rand2);
        sumTextView.setText(Integer.toString(rand1) + " + " + Integer.toString(rand2));
        for(int i = 0; i < 4; i++){
            if(i == answerIndex){
                options.add(answerInt);
            }
            someNumber = b.nextInt(21) + (answerInt-10);
            while(someNumber == answerInt){
                someNumber = b.nextInt(21) + (answerInt-10);
            }
            options.add(someNumber);
        }
        firstOption.setText(Integer.toString(options.get(0)));
        secondOption.setText(Integer.toString(options.get(1)));
        thirdOption.setText(Integer.toString(options.get(2)));
        fourthOption.setText(Integer.toString(options.get(3)));
        Log.i("It's working", "goodone");
    }

    public void showFinalThings(){
        playAgainButton.setVisibility(View.VISIBLE);
        resultTextView.setText("Your score : " + scoreTextView.getText());
        cdt.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        gameLayout = findViewById(R.id.gameLayout);
        timeTextView = findViewById(R.id.timeTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        total = 0;
        score = 0;
        r = new Random();
        b = new Random();
        firstOption = findViewById(R.id.firstOption);
        secondOption = findViewById(R.id.secondOption);
        thirdOption = findViewById(R.id.thirdOption);
        fourthOption = findViewById(R.id.fourthOption);
        resultTextView.setVisibility(View.INVISIBLE);
        all = new Button[]{firstOption, secondOption, thirdOption, fourthOption};

        cdt = new CountDownTimer(30000 + 100, 1000){
            public void onTick(long msp){
                timeTextView.setText(msp/1000 + "s");
            }
            public void onFinish(){
                timeTextView.setText("0s");
                showFinalThings();
            }
        };
        setSum();
    }
}
