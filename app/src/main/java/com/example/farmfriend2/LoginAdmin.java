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

import java.util.Objects;

public class LoginAdmin extends AppCompatActivity {
    private Button buttonLogInAdmin;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        EditText email = findViewById(R.id.emailLoginAdmin);
        EditText password = findViewById(R.id.passwordLoginAdmin);

        buttonLogInAdmin = findViewById(R.id.buttonLogInAdmin);
        buttonLogInAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();
                String passInput = password.getText().toString();

                db.collection("admin_sign_in")
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
                                        Intent intent = new Intent(getApplication(), AdminProfileActivity.class);
                                        startActivity(intent);
                                        Toast.makeText(LoginAdmin.this, "Welcome Admin!"
                                                , Toast.LENGTH_SHORT).show();

                                        Toast.makeText(LoginAdmin.this, "" + TemporaryDB.email
                                                , Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Incorrect password
                                        Toast.makeText(LoginAdmin.this, "Invalid Password"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    // User not found in Firestore
                                    Toast.makeText(LoginAdmin.this, "Check Inputs!"
                                            , Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@androidx.annotation.NonNull Exception e) {
                                Toast.makeText(LoginAdmin.this, "Error" + e
                                        , Toast.LENGTH_SHORT).show();
                                Log.w("FIRESTORE", "Error finding user", e);
                            }
                        });
            }

        });
    }
}

