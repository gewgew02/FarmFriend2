package com.example.farmfriend2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class SettingsActivity extends AppCompatActivity {
    private Button buttonEditAccount, buttonLogout, buttonProfile, buttonNotification, buttonRate, buttonDeleteAccount;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_settings);
        Toast.makeText(SettingsActivity.this, ""+TemporaryDB.email
                , Toast.LENGTH_SHORT).show();
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

        buttonDeleteAccount = findViewById(R.id.buttonDeleteAccount);
        buttonDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("admin_sign_in").document(""+TemporaryDB.email)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(SettingsActivity.this,"Successfully Deleted!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), LogInActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SettingsActivity.this,"Delete FAILED!", Toast.LENGTH_SHORT).show();
                            }
                        });
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