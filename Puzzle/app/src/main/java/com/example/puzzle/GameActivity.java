package com.example.puzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    private GridLayout grid;
    private TextView tvMoves;
    private int gridSize;
    private ArrayList<Bitmap> pieces = new ArrayList<>();
    private ArrayList<Integer> positions = new ArrayList<>();
    private int moves = 0;
    private int selectedPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        grid = findViewById(R.id.puzzleGrid);
        tvMoves = findViewById(R.id.tvMoves);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnUp = findViewById(R.id.btnUp);
        Button btnDown = findViewById(R.id.btnDown);
        Button btnLeft = findViewById(R.id.btnLeft);
        Button btnRight = findViewById(R.id.btnRight);

        Intent intent = getIntent();
        gridSize = intent.getIntExtra("GRID_SIZE", 3);
        int imageRes = intent.getIntExtra("IMAGE_RESOURCE", R.drawable.puzzle1);

        startGame(imageRes);

        btnUp.setOnClickListener(v -> moveSelected(0));
        btnDown.setOnClickListener(v -> moveSelected(1));
        btnLeft.setOnClickListener(v -> moveSelected(2));
        btnRight.setOnClickListener(v -> moveSelected(3));

        btnBack.setOnClickListener(v -> finish());
    }

    private void startGame(int imageRes) {
        grid.removeAllViews();
        grid.setColumnCount(gridSize);
        grid.setRowCount(gridSize);

        Bitmap image = BitmapFactory.decodeResource(getResources(), imageRes);
        if (image == null) {
            finish();
            return;
        }

        int size = Math.min(image.getWidth(), image.getHeight());
        int pieceSize = size / gridSize;

        pieces.clear();
        for (int i = 0; i < gridSize * gridSize; i++) {
            int row = i / gridSize;
            int col = i % gridSize;
            Bitmap piece = Bitmap.createBitmap(image, col * pieceSize, row * pieceSize, pieceSize, pieceSize);
            pieces.add(piece);
        }

        image.recycle();

        positions.clear();
        for (int i = 0; i < gridSize * gridSize; i++) {
            positions.add(i);
        }

        Collections.shuffle(positions);
        createPieces();
    }

    private void createPieces() {
        for (int i = 0; i < gridSize * gridSize; i++) {
            ImageView pieceView = new ImageView(this);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(i % gridSize, 1f);
            params.rowSpec = GridLayout.spec(i / gridSize, 1f);
            pieceView.setLayoutParams(params);

            final int position = i;
            int pieceIndex = positions.get(i);

            pieceView.setImageBitmap(pieces.get(pieceIndex));
            pieceView.setOnClickListener(v -> {
                selectPiece(position);
            });

            grid.addView(pieceView);
        }
    }

    private void selectPiece(int pos) {
        clearSelection();

        selectedPos = pos;
        ImageView iv = (ImageView) grid.getChildAt(pos);
        if (iv != null) {
            iv.setBackgroundColor(0x5500BCD4);
        }
    }

    private void clearSelection() {
        if (selectedPos != -1) {
            ImageView old = (ImageView) grid.getChildAt(selectedPos);
            if (old != null) {
                old.setBackgroundColor(0x00000000);
            }
        }
        selectedPos = -1;
    }

    private void moveSelected(int direction) {
        if (selectedPos == -1) {
            Toast.makeText(this, "Сначала выберите пазл", Toast.LENGTH_SHORT).show();
            return;
        }

        int row = selectedPos / gridSize;
        int col = selectedPos % gridSize;
        int targetRow = row;
        int targetCol = col;

        if (direction == 0) targetRow = row - 1;
        else if (direction == 1) targetRow = row + 1;
        else if (direction == 2) targetCol = col - 1;
        else if (direction == 3) targetCol = col + 1;

        if (targetRow < 0 || targetRow >= gridSize || targetCol < 0 || targetCol >= gridSize) {
            return;
        }

        int targetPos = targetRow * gridSize + targetCol;

        Collections.swap(positions, selectedPos, targetPos);

        moves++;
        tvMoves.setText("Ходы: " + moves);

        updatePieces();
        clearSelection();

        if (checkWin()) {
            Toast.makeText(this, "Победа! Ходы: " + moves, Toast.LENGTH_LONG).show();
        }
    }

    private void updatePieces() {
        for (int i = 0; i < gridSize * gridSize; i++) {
            ImageView iv = (ImageView) grid.getChildAt(i);
            if (iv == null) continue;

            int pieceIndex = positions.get(i);
            iv.setImageBitmap(pieces.get(pieceIndex));

            final int position = i;
            iv.setOnClickListener(v -> {
                selectPiece(position);
            });
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < gridSize * gridSize; i++) {
            if (positions.get(i) != i) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("positions", positions);
        outState.putInt("moves", moves);
        outState.putInt("gridSize", gridSize);
        outState.putInt("selectedPos", selectedPos);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        positions = savedInstanceState.getIntegerArrayList("positions");
        if (positions != null) {
            moves = savedInstanceState.getInt("moves");
            gridSize = savedInstanceState.getInt("gridSize");
            selectedPos = savedInstanceState.getInt("selectedPos", -1);
            tvMoves.setText("Ходы: " + moves);
            updatePieces();
        }
    }
}