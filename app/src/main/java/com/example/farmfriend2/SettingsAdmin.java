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

public class SettingsAdmin extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button setBtnEditAdmin, setBtnProfileAdmin, setBtnNotificationAdmin, setBtnRateAdmin, setBtnDeleteAdmin, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin);

        setBtnEditAdmin = findViewById(R.id.setBtnEditAdmin);
        setBtnEditAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), EditAdmin.class);
                startActivity(intent);
            }
        });

        setBtnProfileAdmin = findViewById(R.id.setBtnProfileAdmin);
        setBtnProfileAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), AdminProfileActivity.class);
                startActivity(intent);
            }
        });

        setBtnNotificationAdmin = findViewById(R.id.setBtnNotificationAdmin);
        setBtnNotificationAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Notifications.class);
                startActivity(intent);
            }
        });

        setBtnRateAdmin = findViewById(R.id.setBtnRateAdmin);
        setBtnRateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Rate.class);
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


        setBtnDeleteAdmin = findViewById(R.id.setBtnDeleteAdmin);
        setBtnDeleteAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("admin_sign_in").document(""+TemporaryDB.email)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SettingsAdmin.this,"Successfully Deleted!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingsAdmin.this,"Delete FAILED!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}