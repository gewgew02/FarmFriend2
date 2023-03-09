package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LGUProfileActivity extends AppCompatActivity {
    private Button buttonSettings, buttonCheckWeather, buttonSuggestedCropstoPlant, buttonLogout, buttonSearchPrograms, buttonViewPrograms;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lguprofile);

        TextView LGUFirstName = findViewById(R.id.textViewName);
        TextView LGUAddress = findViewById(R.id.textViewAddress);
        TextView LGUCategory = findViewById(R.id.textViewCategory);
        TextView LGUContact = findViewById(R.id.textViewLGUContact);


        db.collection("LGU_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                        LGUFirstName.setText(documentSnapshot2.getString("first_name") + " " + documentSnapshot2.getString("last_name"));
                        LGUAddress.setText(documentSnapshot2.getString("address"));
                        LGUCategory.setText(documentSnapshot2.getString("category"));
                        LGUContact.setText(documentSnapshot2.getString("contact_num"));

                        TemporaryDB.email = documentSnapshot2.getString("email");
                    } else {
                        // User not found in Firestore
                        Toast.makeText(LGUProfileActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });

        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingsLGU.class);
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