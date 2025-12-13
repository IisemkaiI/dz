package com.example.artchallenge;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class ChallengeActivity extends AppCompatActivity {

    private TextView challengeText;
    private TextView difficultyText;
    private Button generateButton;
    private Button backButton;
    private String difficulty;

    private String[] noviceChallenges = {
            "Нарисуй свою мечту", "Нарисуй гриб", "Нарисуй улыбающееся солнце",
            "Нарисуй дом с привидениями", "Нарисуй дерево с глазами", "Нарисуй летающую тарелку",
            "Нарисуй чашку с очками", "Нарисуй книгу с крыльями", "Нарисуй облако в форме животного",
            "Нарисуй пиццу с эмоциями", "Нарисуй кактус в ковбойской шляпе", "Нарисуй лужу с отражением луны",
            "Нарисуй ключ от счастья", "Нарисуй свечу державщуюся из последних сил", "Нарисуй предателя из Among as",
            "Нарисуй часы с фруктами вместо цифр", "Нарисуй логотип игры",
            "Нарисуй лодку из листа", "Нарисуй замок в виде замка", "Нарисуй карандаш, рисующий сам себя"
    };

    private String[] amateurChallenges = {
            "Нарисуй лес, где деревья танцуют", "Нарисуй город под водой", "Нарисуй чайную церемонию на облаке",
            "Нарисуй библиотеку в старом дереве", "Нарисуй музыкальный инструмент из природы",
            "Нарисуй отражение города в луже", "Нарисуй кота-путешественника с картой",
            "Нарисуй сад, где цветы светятся ночью", "Нарисуй мост между мирами",
            "Нарисуй старую лампу, из которой выходит не джинн, а...", "Нарисуй часы, показывающие разные времена года",
            "Нарисуй дверь в другой сезон", "Нарисуй корабль, плывущий по небу",
            "Нарисуй дерево с домами вместо листьев", "Нарисуй рыбу, летящую среди облаков",
            "Нарисуй город из конфет", "Нарисуй механическую птицу", "Нарисуй реку из звезд",
            "Нарисуй горы, которые шепчут", "Нарисуй зеркало, показывающее воспоминания",
            "Нарисуй театр для насекомых", "Нарисуй парящие острова", "Нарисуй лес из стекла",
            "Нарисуй океан в бутылке", "Нарисуй закат на другой планете"
    };

    private String[] proChallenges = {
            "Нарисуй эмоцию 'ностальгия' через абстракцию", "Нарисуй звук тишины",
            "Нарисуй сон, который нельзя забыть", "Нарисуй время как физический объект",
            "Нарисуй разговор между тенью и светом", "Нарисуй музыку в визуальной форме",
            "Нарисуй воспоминание, которое меняет цвет", "Нарисуй внутренний мир интроверта",
            "Нарисуй переход от дня к ночи в одном кадре", "Нарисуй цивилизацию, живущую на спинке кита",
            "Нарисуй архитектуру будущего с элементами природы", "Нарисуй бота, выращивающего эмоции",
            "Нарисуй квестовую карту для поиска вдохновения", "Нарисуй механическое сердце города",
            "Нарисуй библиотеку, где книги оживают", "Нарисуй город, построенный на спящем драконе",
            "Нарисуй портал в детскую мечту", "Нарисуй алхимическую лабораторию",
            "Нарисуй сад, где растут не цветы, а мелодии", "Нарисуй подводную обсерваторию",
            "Нарисуй часы, отсчитывающие не время, а эмоции", "Нарисуй мост из радуги в черно-белый мир",
            "Нарисуй дерево, корни которого уходят в разные эпохи", "Нарисуй карту звёздного неба на коже",
            "Нарисуй лабиринт, который меняется при каждом взгляде", "Нарисуй океан воспоминаний",
            "Нарисуй город, где здания растут как растения", "Нарисуй книгу, страницы которой - разные миры",
            "Нарисуй машину, которая собирает сны", "Нарисуй отражение души в зеркале"
    };

    private String[] artStyles = {
            "в стиле аниме", "в стиле пиксель-арт", "в стиле импрессионизма",
            "в стиле сюрреализма", "в стиле киберпанк", "в стиле стимпанк",
            "в минималистичном стиле", "в детском стиле", "в готическом стиле",
            "в фэнтезийном стиле", "в научно-фантастическом стиле", "в винтажном стиле"
    };

    private String[] techniques = {
            "с помощью наброска карандаша", "с помощью акварельной техники", "с помощью цифровой живописи",
            "с помощью маркерного скетчинга", "с помощью угля и сангины", "с помощью пастельных мелков",
            "с помощью туши и пера", "с помощью акриловой краски", "с помощью масляной живописи",
            "с помощью коллажной техники", "с помощью смешанной техники", "с помощью монохромной гаммы"
    };

    private String[] constraints = {
            "используя только 3 цвета", "не отрывая руки от бумаги", "за 5 минут",
            "с закрытыми глазами (набросок)", "левой рукой (или правой, если вы левша)",
            "в перевернутом виде", "используя геометрические формы", "без прямых линий",
            "только штрихами", "только точками", "одной непрерывной линией",
            "вверх ногами", "в маленьком масштабе", "в огромном масштабе"
    };

    private String[] animals = {
            "с лисой", "с капибарой", "с львом",
            "с пингвином", "с обезьяной", "с мышкой",
            "с Папой грибом", "со змеёй", "с оленем"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        challengeText = findViewById(R.id.challengeText);
        difficultyText = findViewById(R.id.difficultyText);
        generateButton = findViewById(R.id.generateButton);
        backButton = findViewById(R.id.backButton);

        difficulty = getIntent().getStringExtra("DIFFICULTY");
        difficultyText.setText("Уровень: " + difficulty);

        generateRandomChallenge();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomChallenge();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void generateRandomChallenge() {
        String[] challenges;
        Random random = new Random();

        switch(difficulty) {
            case "Новичок":
                challenges = noviceChallenges;
                break;
            case "Любитель":
                challenges = amateurChallenges;
                break;
            case "Профессионал":
                challenges = proChallenges;
                break;
            default:
                challenges = noviceChallenges;
        }

        String baseChallenge = challenges[random.nextInt(challenges.length)];

        StringBuilder fullChallenge = new StringBuilder(baseChallenge);

        switch(difficulty) {
            case "Новичок":
                if (random.nextBoolean()) {
                    fullChallenge.append(" ").append(techniques[random.nextInt(techniques.length)]);
                }
                break;

            case "Любитель":
                fullChallenge.append(" ").append(techniques[random.nextInt(techniques.length)]);
                fullChallenge.append(", ").append(animals[random.nextInt(animals.length)]);
                if (random.nextBoolean()) {
                    fullChallenge.append(", ").append(constraints[random.nextInt(constraints.length)]);
                }
                break;

            case "Профессионал":
                fullChallenge.append(" ").append(artStyles[random.nextInt(artStyles.length)]);
                fullChallenge.append(", ").append(techniques[random.nextInt(techniques.length)]);
                fullChallenge.append(", ").append(animals[random.nextInt(animals.length)]);
                fullChallenge.append(", ").append(constraints[random.nextInt(constraints.length)]);
                break;
        }

        String[] timeLimits = {"(время: 10 минут)", "(время: 20 минут)",
                "(время: 30 минут)", "(время: 45 минут)",
                "(время: 1 час)", "(без ограничения времени)"};
        fullChallenge.append(" ").append(timeLimits[random.nextInt(timeLimits.length)]);

        challengeText.setText(fullChallenge.toString());

        String toastMessage;
        switch(difficulty) {
            case "Новичок":
                toastMessage = "Начнем с простого";
                break;
            case "Любитель":
                toastMessage = "Будет чуть посложнее";
                break;
            case "Профессионал":
                toastMessage = "Покажи на что способен";
                break;
            default:
                toastMessage = "Новый челлендж создан";
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}