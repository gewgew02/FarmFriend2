package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminProfileActivity extends AppCompatActivity {
    private Button buttonSettings, buttonCheckWeather, buttonSuggestedCropstoPlant, buttonViewUserFarmer, buttonViewUsersLGU, buttonLogout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        TextView adminFirstName = findViewById(R.id.textViewName);
        TextView adminAddress = findViewById(R.id.textViewAddress);
        TextView adminCategory = findViewById(R.id.textViewCategory);
        TextView adminContact = findViewById(R.id.textViewAdminContact);

        db.collection("admin_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                       adminFirstName.setText(documentSnapshot2.getString("first_name") + " " + documentSnapshot2.getString("last_name"));
                       adminAddress.setText(documentSnapshot2.getString("address"));
                       adminCategory.setText(documentSnapshot2.getString("category"));
                       adminContact.setText(documentSnapshot2.getString("contact_num"));

                        TemporaryDB.email = documentSnapshot2.getString("email");
                    } else {

                        Toast.makeText(AdminProfileActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
//        imageViewAdmin1 = findViewById(R.id.imageViewAdmin);
//        imageViewAdmin1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGetContent.launch("image/*");
//            }
//        });
        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingsAdmin.class);
                startActivity(intent);
            }
        });
//
        buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
//
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
//
        buttonViewUserFarmer = findViewById(R.id.buttonViewUserFarmer);
        buttonViewUserFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), FarmerListsActivity.class);
                startActivity(intent);
            }
        });
//
        buttonViewUsersLGU = findViewById(R.id.buttonViewUsersLGU);
        buttonViewUsersLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LGUListsActivity.class);
                startActivity(intent);
            }
        });
}
}
