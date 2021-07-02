package com.assignment.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void level1(View view) {
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("range",20);
        startActivity(intent);
    }
    public void level2(View view) {
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("range",50);
        startActivity(intent);
    }
    public void level3(View view) {
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("range",100);
        startActivity(intent);
    }
}