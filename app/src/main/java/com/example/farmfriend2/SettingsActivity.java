package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {
    private Button buttonEditAccount, buttonLogout, buttonProfile, buttonNotification, buttonRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonEditAccount = findViewById(R.id.buttonEditAccount);
        buttonEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
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

        buttonRate = findViewById(R.id.buttonRate);
        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Rate.class);
                startActivity(intent);
            }
        });

        buttonNotification = findViewById(R.id.buttonNotification);
        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Notifications.class);
                startActivity(intent);
            }
        });
        /**
         if(
            buttonProfile = findViewById(R.id.buttonProfile);
            buttonProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), LGUProfileActivity.class);
                    startActivity(intent);
                }
            });

         */
    }
}