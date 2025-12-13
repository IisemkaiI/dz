package com.example.greatartistsgallery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ArtistDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        int artistId = getIntent().getIntExtra("artist_id", 1);

        TextView nameTextView = findViewById(R.id.artistName);
        ImageView portraitImageView = findViewById(R.id.artistPortrait);
        TextView bioTextView = findViewById(R.id.artistBio);
        ImageView painting1ImageView = findViewById(R.id.painting1);
        ImageView painting2ImageView = findViewById(R.id.painting2);

        switch (artistId) {
            case 1: // Леонардо да Винчи
                nameTextView.setText("Леонардо да Винчи");
                bioTextView.setText("Леонардо да Винчи (1452-1519) - итальянский художник, учёный, изобретатель, писатель, музыкант. Один из крупнейших представителей искусства Высокого Возрождения. Яркий пример «универсального человека».");
                portraitImageView.setImageResource(R.drawable.leonardo);
                painting1ImageView.setImageResource(R.drawable.mona_lisa);
                painting2ImageView.setImageResource(R.drawable.last_supper);
                break;

            case 2: // Винсент Ван Гог
                nameTextView.setText("Винсент Ван Гог");
                bioTextView.setText("Винсент Ван Гог (1853-1890) - нидерландский художник-постимпрессионист. За 10 лет создал около 2100 произведений. Его работы оказали огромное влияние на искусство XX века.");
                portraitImageView.setImageResource(R.drawable.vangogh);
                painting1ImageView.setImageResource(R.drawable.starry_night);
                painting2ImageView.setImageResource(R.drawable.sunflowers);
                break;

            case 3: // Пабло Пикассо
                nameTextView.setText("Пабло Пикассо");
                bioTextView.setText("Пабло Пикассо (1881-1973) - испанский художник, скульптор, график, керамист и дизайнер. Основоположник кубизма. Один из самых влиятельных художников XX века.");
                portraitImageView.setImageResource(R.drawable.picasso);
                painting1ImageView.setImageResource(R.drawable.guernica);
                painting2ImageView.setImageResource(R.drawable.girl_before_mirror);
                break;

            case 4: // Рембрандт
                nameTextView.setText("Рембрандт");
                bioTextView.setText("Рембрандт Харменс ван Рейн (1606-1669) - голландский художник, гравёр, великий мастер светотени. Крупнейший представитель золотого века голландской живописи.");
                portraitImageView.setImageResource(R.drawable.rembrandt);
                painting1ImageView.setImageResource(R.drawable.night_watch);
                painting2ImageView.setImageResource(R.drawable.self_portrait_rembrandt);
                break;

            case 5: // Клод Моне
                nameTextView.setText("Клод Моне");
                bioTextView.setText("Клод Моне (1840-1926) - французский художник, один из основателей импрессионизма. Известен своими пейзажами, сериями картин с разным освещением.");
                portraitImageView.setImageResource(R.drawable.monet);
                painting1ImageView.setImageResource(R.drawable.water_lilies);
                painting2ImageView.setImageResource(R.drawable.impression_sunrise);
                break;

            default:
                nameTextView.setText("Художник");
                bioTextView.setText("Информация о художнике");
                portraitImageView.setImageResource(R.drawable.leonardo);
                painting1ImageView.setImageResource(R.drawable.mona_lisa);
                painting2ImageView.setImageResource(R.drawable.last_supper);
        }
    }
}