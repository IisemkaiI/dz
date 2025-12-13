package com.example.artchallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup difficultyGroup;
    private Button startButton;
    private String selectedDifficulty = "Новичок";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficultyGroup = findViewById(R.id.difficultyGroup);
        startButton = findViewById(R.id.startButton);

        difficultyGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioEasy) {
                    selectedDifficulty = "Новичок";
                } else if (checkedId == R.id.radioMedium) {
                    selectedDifficulty = "Любитель";
                } else if (checkedId == R.id.radioHard) {
                    selectedDifficulty = "Профессионал";
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сначала открываем активность для угадывания
                Intent intent = new Intent(MainActivity.this, GuessActivity.class);
                intent.putExtra("DIFFICULTY", selectedDifficulty);
                startActivity(intent);
            }
        });
    }
}