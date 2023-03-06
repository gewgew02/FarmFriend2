package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminProfileActivity extends AppCompatActivity {
    private Button buttonSettings, buttonCheckWeather, buttonSuggestedCropstoPlant, buttonViewUserFarmer, buttonViewUsersLGU, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

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
                Intent intent = new Intent(getApplication(), CheckWeatherAdminActivity.class);
                startActivity(intent);
            }
        });

        buttonSuggestedCropstoPlant = findViewById(R.id.buttonSuggestedCropstoPlant);
        buttonSuggestedCropstoPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SuggestedCropsAdminActivity.class);
                startActivity(intent);
            }
        });

        buttonViewUserFarmer = findViewById(R.id.buttonViewUserFarmer);
        buttonViewUserFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ViewFarmerActivity.class);
                startActivity(intent);
            }
        });

        buttonViewUsersLGU = findViewById(R.id.buttonViewUsersLGU);
        buttonViewUsersLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ViewLGUActivity.class);
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