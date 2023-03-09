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

public class SettingsLGU extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button setBtnEditLGU, buttonLogout, setBtnProfileLGU, setBtnNotificationLGU, setBtnRateLGU, setBtnDeleteLGU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_lgu);

        setBtnEditLGU = findViewById(R.id.setBtnEditLGU);
        setBtnEditLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditLGU.class);
                startActivity(intent);
            }
        });

        setBtnProfileLGU = findViewById(R.id.setBtnProfileLGU);
        setBtnProfileLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
                startActivity(intent);
            }
        });

        setBtnNotificationLGU = findViewById(R.id.setBtnNotificationLGU);
        setBtnNotificationLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditActivity.class);
                startActivity(intent);
            }
        });

        setBtnRateLGU = findViewById(R.id.setBtnRateLGU);
        setBtnRateLGU.setOnClickListener(new View.OnClickListener() {
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

        setBtnDeleteLGU = findViewById(R.id.setBtnDeleteLGU);
        setBtnDeleteLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("LGU_sign_in").document(""+TemporaryDB.email)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SettingsLGU.this,"Successfully Deleted!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingsLGU.this,"Delete FAILED!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}