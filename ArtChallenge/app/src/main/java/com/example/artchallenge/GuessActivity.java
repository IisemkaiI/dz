package com.example.artchallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    private ImageView guessImageView;
    private EditText guessEditText;
    private Button submitButton;
    private Button skipButton;

    private String[] imageAnswers = {
            "солнце", "дом", "дерево", "собака", "цветок",
            "машина", "книга", "луна", "облако", "птица"
    };

    private int[] imageResources = {
            R.drawable.sun, R.drawable.house, R.drawable.tree,
            R.drawable.dog, R.drawable.flower, R.drawable.car,
            R.drawable.book, R.drawable.moon,
            R.drawable.cloud, R.drawable.bird
    };

    private String currentAnswer;
    private String difficulty;
    private int currentImageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        guessImageView = findViewById(R.id.guessImageView);
        guessEditText = findViewById(R.id.guessEditText);
        submitButton = findViewById(R.id.submitButton);
        skipButton = findViewById(R.id.skipButton);

        difficulty = getIntent().getStringExtra("DIFFICULTY");

        Random random = new Random();
        currentImageIndex = random.nextInt(imageResources.length);
        currentAnswer = imageAnswers[currentImageIndex];

        guessImageView.setImageResource(imageResources[currentImageIndex]);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userGuess = guessEditText.getText().toString().trim();
                String correctAnswer = currentAnswer;

                if (userGuess.isEmpty()) {
                    Toast.makeText(GuessActivity.this, "Введите ваш ответ!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Сравниваем без учета регистра
                if (userGuess.equalsIgnoreCase(correctAnswer)) {
                    Toast.makeText(GuessActivity.this, "Правильно! Возвращаем в меню.", Toast.LENGTH_SHORT).show();
                    // ВОЗВРАЩАЕМСЯ В МЕНЮ
                    Intent intent = new Intent(GuessActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(GuessActivity.this, "Неа! Переходим к челленджу.", Toast.LENGTH_SHORT).show();
                    // ПЕРЕХОДИМ К ЧЕЛЛЕНДЖУ
                    goToChallenge();
                }
            }
        });

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GuessActivity.this, "Пропускаем. Это " + currentAnswer, Toast.LENGTH_SHORT).show();
                goToChallenge();
            }
        });
    }

    private void goToChallenge() {
        Intent intent = new Intent(GuessActivity.this, ChallengeActivity.class);
        intent.putExtra("DIFFICULTY", difficulty);
        startActivity(intent);
        finish();
    }
}