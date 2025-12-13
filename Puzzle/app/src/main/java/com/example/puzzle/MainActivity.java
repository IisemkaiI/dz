package com.example.puzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private RadioGroup difficultyGroup;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        difficultyGroup = findViewById(R.id.difficultyGroup);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    private void startGame() {
        int difficultyId = difficultyGroup.getCheckedRadioButtonId();
        int gridSize;
        int imageResource;

        if (difficultyId == R.id.radioEasy) {
            gridSize = 3;
            imageResource = R.drawable.puzzle1;
        } else if (difficultyId == R.id.radioMedium) {
            gridSize = 4;
            imageResource = R.drawable.puzzle2;
        } else {
            gridSize = 5;
            imageResource = R.drawable.puzzle3;
        }

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("GRID_SIZE", gridSize);
        intent.putExtra("IMAGE_RESOURCE", imageResource);
        startActivity(intent);
    }
}