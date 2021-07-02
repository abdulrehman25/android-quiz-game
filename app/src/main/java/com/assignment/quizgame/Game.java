package com.assignment.quizgame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView txtLevel;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    ArrayList<String> operations = new ArrayList<String>();
    int locationOfCorrectAnswer;
    int randomOperation;
    int score = 0;
    int numberOfQuestions = 0;

    private Button btnOption1;
    private Button btnOption2;
    private Button btnOption3;
    private Button btnOption4;
    private Button btnPlayAgain;
    private Button btnCancel;
    private Button startButton;


    private TextView scoreText;
    private TextView responseText;
    private TextView queryText;
    private TextView timerText;
    //By default it will be 20
    int gameRange = 20;
    GridLayout gridLayout;
    LinearLayout linearLayout;
    LinearLayout linearLayoutStats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

//        String range = bundle.getInt("range");
        gameRange = bundle.getInt("range");

        operations.add("+");
        operations.add("-");
        operations.add("*");
        operations.add("/");

        btnCancel = findViewById(R.id.btnCancel);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        queryText = findViewById(R.id.queryText);
        responseText = findViewById(R.id.responseText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        gridLayout = findViewById(R.id.gridLayout);
        startButton = findViewById(R.id.startButton);
        linearLayout = findViewById(R.id.playAgainBtns);
        linearLayoutStats = findViewById(R.id.stats);
        // generateQuestion();
    }

    public void generateQuestion() {
        Random rand = new Random();
        int a = rand.nextInt(gameRange + 1);
        int b = rand.nextInt(gameRange + 1);
        randomOperation = rand.nextInt(4);
        queryText.setText(Integer.toString(a) +" " + operations.get(randomOperation) +""+ Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                switch (operations.get(randomOperation)){
                    case "+":
                        answers.add(a + b);
                        break;
                    case "-":
                        answers.add(a - b);
                        break;
                    case "*":
                        answers.add(a * b);
                        break;
                    case "/":
                        answers.add(a / b);
                        break;
                }
            } else {
                switch (operations.get(randomOperation)){
                    case "+":
                        incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        while (incorrectAnswer == a + b) {
                            incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        }
                        break;
                    case "-":
                        incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        while (incorrectAnswer == a - b) {
                            incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1) - a;
                        }
                        break;
                    case "*":
                        incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        while (incorrectAnswer == a * b) {
                            incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        }
                        break;
                    case "/":
                        incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        while (incorrectAnswer == a / b) {
                            incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        }
                        break;
                    default:
                        incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        while (incorrectAnswer == a + b) {
                            incorrectAnswer = rand.nextInt(( gameRange * 2 ) + 1);
                        }
                        break;
                }
                answers.add(incorrectAnswer);
            }
        }
        btnOption1.setText(Integer.toString(answers.get(0)));
        btnOption2.setText(Integer.toString(answers.get(1)));
        btnOption3.setText(Integer.toString(answers.get(2)));
        btnOption4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        responseText.setVisibility(View.VISIBLE);
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            responseText.setText("Correct!");
        } else {
            responseText.setText("Wrong!");
        }
        numberOfQuestions++;
        scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;

        timerText.setText("30s");
        scoreText.setText("0/0");
        responseText.setText("");
        responseText.setVisibility(View.INVISIBLE);
        btnPlayAgain.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerText.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                btnPlayAgain.setVisibility(View.VISIBLE);
                timerText.setText("0s");
                gridLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                responseText.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
            }
        }.start();
    }
    public void start(View view) {

        startButton.setVisibility(View.GONE);
        gridLayout.setVisibility(GridLayout.VISIBLE);
        linearLayoutStats.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.btnPlayAgain));

    }
    public void cancel(View view){
        System.exit(0);
    }
}