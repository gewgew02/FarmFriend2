package com.example.farmfriend2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsFarmer extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button setBtnEditFarmer, buttonLogout, setBtnProfileFarmer, setBtnNotificationFarmer, setBtnRateFarmer, setBtnDeleteFarmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_farmer);

        setBtnEditFarmer = findViewById(R.id.setBtnEditFarmer);
        setBtnEditFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditFarmer.class);
                startActivity(intent);
            }
        });

        setBtnProfileFarmer = findViewById(R.id.setBtnProfileFarmer);
        setBtnProfileFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
                startActivity(intent);
            }
        });

        setBtnNotificationFarmer = findViewById(R.id.setBtnNotificationFarmer);
        setBtnNotificationFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
                startActivity(intent);
            }
        });

        setBtnRateFarmer = findViewById(R.id.setBtnRateFarmer);
        setBtnRateFarmer.setOnClickListener(new View.OnClickListener() {
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

        setBtnDeleteFarmer = findViewById(R.id.setBtnDeleteFarmer);
        setBtnDeleteFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("farmer_sign_in").document(""+TemporaryDB.email)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SettingsFarmer.this,"Successfully Deleted!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingsFarmer.this,"Delete FAILED!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}