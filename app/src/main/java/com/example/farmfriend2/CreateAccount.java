package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateAccount extends AppCompatActivity {
    Button buttonFarmer, buttonLGU, buttonAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        buttonFarmer = findViewById(R.id.buttonFarmer);
        buttonFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SignInFarmerActivity.class);
                startActivity(intent);
            }
        });

        buttonLGU = findViewById(R.id.buttonLGU);
        buttonLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SignInLGUActivity.class);
                startActivity(intent);
            }
        });

        buttonAdmin = findViewById(R.id.buttonAdmin);
        buttonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SignInAdminActivity.class);
                startActivity(intent);
            }
        });
    }
}