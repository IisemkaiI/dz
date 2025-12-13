package com.example.memegenerator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button buttonGenerate;
    private ImageView imageViewMeme;

    private String[] templates = {
            "starry_night", "mona_lisa", "mk",
            "skilet", "kot", "scream", "aomine",
            "hex", "i"
    };

    private String[] topPhrases = {
            "современное искусство",
            "когда башмачки",
            "кэшбек 100% каждую пятницу",
            "смешная шутка",
            "Когда на зачёте по ИЗО говоришь:»"
    };

    private String[] bottomPhrases = {
            "...сандалики",
            "...Недооцененные шедевр",
            "...Сургут, Россия",
            "...Неточно",
            "...огурец"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupButton();
    }

    private void initViews() {
        buttonGenerate = findViewById(R.id.buttonGenerate);
        imageViewMeme = findViewById(R.id.imageViewMeme);
    }

    private void setupButton() {
        buttonGenerate.setOnClickListener(v -> generateMeme());
    }

    private void generateMeme() {
        clearImageView();

        Random rand = new Random();
        int templateIndex = rand.nextInt(templates.length);
        String templateName = templates[templateIndex];

        Bitmap original = decodeSampledBitmap(templateName, 800, 800);
        if (original == null) {
            Toast.makeText(this, "Изображение не найдено: " + templateName, Toast.LENGTH_SHORT).show();
            return;
        }

        String top = topPhrases[rand.nextInt(topPhrases.length)];
        String bottom = bottomPhrases[rand.nextInt(bottomPhrases.length)];

        int whichEmpty = rand.nextInt(3);

        if (whichEmpty == 0) {
            top = "";
        } else if (whichEmpty == 1) {
            bottom = "";
        }

        try {
            Bitmap meme = addTextToBitmap(original, top.toUpperCase(), bottom.toUpperCase());
            if (meme != null) {
                imageViewMeme.setImageBitmap(meme);
            }
        } catch (OutOfMemoryError e) {
            Toast.makeText(this, "Не хватило памяти... Попробуйте снова", Toast.LENGTH_SHORT).show();
            System.gc();
        }
    }

    private Bitmap decodeSampledBitmap(String resourceName, int reqWidth, int reqHeight) {
        int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
        if (resId == 0) return null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), resId, options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private void clearImageView() {
        Drawable drawable = imageViewMeme.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
        imageViewMeme.setImageDrawable(null);
    }

    private Bitmap addTextToBitmap(Bitmap bitmap, String top, String bottom) {
        if (bitmap == null) {
            return null;
        }

        Bitmap.Config config = bitmap.getConfig();
        if (config == null) config = Bitmap.Config.ARGB_8888;

        bitmap = bitmap.copy(config, true);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setShadowLayer(8, 0, 0, Color.BLACK);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (!top.isEmpty()) {
            canvas.drawText(top, width / 2f, 80 + paint.getTextSize(), paint);
        }
        if (!bottom.isEmpty()) {
            canvas.drawText(bottom, width / 2f, height - 40, paint);
        }

        return bitmap;
    }

}