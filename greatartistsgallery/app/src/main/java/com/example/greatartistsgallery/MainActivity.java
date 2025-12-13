package com.example.greatartistsgallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void openLeonardo(View view) {
        openArtistDetail(1);
    }

    public void openVanGogh(View view) {
        openArtistDetail(2);
    }

    public void openPicasso(View view) {
        openArtistDetail(3);
    }

    public void openRembrandt(View view) {
        openArtistDetail(4);
    }

    public void openMonet(View view) {
        openArtistDetail(5);
    }

    private void openArtistDetail(int artistId) {
        Intent intent = new Intent(this, ArtistDetailActivity.class);
        intent.putExtra("artist_id", artistId);
        startActivity(intent);
    }
}