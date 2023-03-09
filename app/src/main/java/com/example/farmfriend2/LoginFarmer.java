package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;

public class LoginFarmer extends AppCompatActivity {
    private Button buttonLogInFarmer;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_farmer);
        EditText email = findViewById(R.id.emailLoginFarmer);
        EditText password = findViewById(R.id.passwordLoginFarmer);

        buttonLogInFarmer = findViewById(R.id.buttonLogInFarmer);
        buttonLogInFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();
                String passInput = password.getText().toString();

                db.collection("farmer_sign_in")
                        .whereEqualTo("email", emailInput)
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                                    String userPassword = documentSnapshot.getString("password");

                                    TemporaryDB.email = documentSnapshot.getString("email");

                                    if (Objects.equals(userPassword, String.valueOf(passInput))) {
                                        // User logged in successfully
                                        Intent intent = new Intent(getApplication(), FarmerProfileActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginFarmer.this, "Welcome Farmer!"
                                                , Toast.LENGTH_SHORT).show();

                                        Toast.makeText(LoginFarmer.this, "" + TemporaryDB.email
                                                , Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Incorrect password
                                        Toast.makeText(LoginFarmer.this, "Invalid Password"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    // User not found in Firestore
                                    Toast.makeText(LoginFarmer.this, "Check Inputs!"
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                Toast.makeText(LoginFarmer.this, "Error" + e
                                        , Toast.LENGTH_SHORT).show();
                                Log.w("FIRESTORE", "Error finding user", e);
                            }
                        });
            }

        });
    }
}