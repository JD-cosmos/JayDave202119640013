package com.example.question_1_external_exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View box;
    private Button upButton, downButton, leftButton, rightButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        box = findViewById(R.id.box);
        upButton = findViewById(R.id.up_button);
        downButton = findViewById(R.id.down_button);
        leftButton = findViewById(R.id.left_button);
        rightButton = findViewById(R.id.right_button);

        upButton.setOnClickListener(v -> animateBox("up"));
        downButton.setOnClickListener(v -> animateBox("down"));
        leftButton.setOnClickListener(v -> animateBox("left"));
        rightButton.setOnClickListener(v -> animateBox("right"));
    }

    private void animateBox(String direction) {
        float translationY = 0;
        float translationX = 0;

        switch (direction) {
            case "up":
                translationY = -500;
                break;
            case "down":
                translationY = 500;
                break;
            case "left":
                translationX = -500;
                break;
            case "right":
                translationX = 500;
                break;
        }

        box.animate()
                .translationX(translationX)
                .translationY(translationY)
                .setDuration(300)
                .withEndAction(() -> {
                    box.setTranslationX(0);
                    box.setTranslationY(0);
                });
    }
}
