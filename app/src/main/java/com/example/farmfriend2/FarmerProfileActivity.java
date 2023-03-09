package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FarmerProfileActivity extends AppCompatActivity {
    private Button buttonCheckWeather, buttonSearchPrograms, buttonSuggestedCropstoPlant, buttonSettings, buttonLogout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_profile);

        TextView farmerFirstName = findViewById(R.id.textViewName);
        TextView farmerAddress = findViewById(R.id.textViewAddress);
        TextView farmerCategory = findViewById(R.id.textViewCategory);
        TextView farmerContact = findViewById(R.id.textViewFarmerContact);

        db.collection("farmer_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                        farmerFirstName.setText(documentSnapshot2.getString("first_name") + " " + documentSnapshot2.getString("last_name"));
                        farmerAddress.setText(documentSnapshot2.getString("address"));
                        farmerCategory.setText(documentSnapshot2.getString("category"));
                        farmerContact.setText(documentSnapshot2.getString("contact_num"));

                        TemporaryDB.email = documentSnapshot2.getString("email");
                    } else {
                        // User not found in Firestore
                        Toast.makeText(FarmerProfileActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });

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
                Intent intent = new Intent(getApplication(), SettingsFarmer.class);
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
