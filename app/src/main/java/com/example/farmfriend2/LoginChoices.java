package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginChoices extends AppCompatActivity {
    Button buttonFarmer, buttonLGU, buttonAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choices);
        buttonFarmer = findViewById(R.id.buttonFarmer);
        buttonFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LoginFarmer.class);
                startActivity(intent);
            }
        });

        buttonLGU = findViewById(R.id.buttonLGU);
        buttonLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LoginLGU.class);
                startActivity(intent);
            }
        });

        buttonAdmin = findViewById(R.id.buttonAdmin);
        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LoginAdmin.class);
                startActivity(intent);
            }
        });
    }
}