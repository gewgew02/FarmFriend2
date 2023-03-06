package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LGUProfileActivity extends AppCompatActivity {
    private Button buttonSettings, buttonCheckWeather, buttonSuggestedCropstoPlant, buttonLogout, buttonSearchPrograms, buttonViewPrograms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lguprofile);

        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        buttonCheckWeather = findViewById(R.id.buttonCheckWeather);
        buttonCheckWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CheckWeatherLGUActivity.class);
                startActivity(intent);
            }
        });

        buttonSuggestedCropstoPlant = findViewById(R.id.buttonSuggestedCropstoPlant);
        buttonSuggestedCropstoPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SuggestedCropsLGUActivity.class);
                startActivity(intent);
            }
        });

        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });

        buttonSearchPrograms = findViewById(R.id.buttonSearchPrograms);
        buttonSearchPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SearchProgramsLGUActivity.class);
                startActivity(intent);
            }
        });

        buttonViewPrograms = findViewById(R.id.buttonViewPrograms);
        buttonViewPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Programs.class);
                startActivity(intent);
            }
        });
    }
}