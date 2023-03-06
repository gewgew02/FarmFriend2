package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerProfileActivity extends AppCompatActivity {
    private Button buttonCheckWeather, buttonSearchPrograms, buttonSuggestedCropstoPlant, buttonSettings, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_profile);

        buttonCheckWeather = findViewById(R.id.buttonCheckWeather);
        buttonCheckWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CheckWeatherFarmerActivity.class);
                startActivity(intent);
            }
        });

        buttonSearchPrograms = findViewById(R.id.buttonSearchPrograms);
        buttonSearchPrograms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SearchProgramsFarmerActivity.class);
                startActivity(intent);
            }
        });

        buttonSuggestedCropstoPlant = findViewById(R.id.buttonSuggestedCropstoPlant);
        buttonSuggestedCropstoPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SuggestedCropsFarmerActivity.class);
                startActivity(intent);
            }
        });

        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingsActivity.class);
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
    }
}
