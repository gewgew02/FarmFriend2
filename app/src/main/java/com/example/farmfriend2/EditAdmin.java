package com.example.farmfriend2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditAdmin extends AppCompatActivity {
    private Button buttonSettings, buttonLogout, buttonSaveEditAdmin;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);

        EditText adminFirstName = findViewById(R.id.adminFirstName);
        EditText adminLastName = findViewById(R.id.adminLastName);
        EditText adminCategory = findViewById(R.id.adminCategory);
        EditText adminAddress = findViewById(R.id.adminAddress);
        EditText adminContactNum = findViewById(R.id.adminContactNum);
        EditText adminPassword = findViewById(R.id.adminPassword);
        //TextView adminId = findViewById(R.id.adminId);

        db.collection("admin_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                        adminFirstName.setText(documentSnapshot2.getString("first_name"));
                        adminLastName.setText(documentSnapshot2.getString("last_name"));
                        adminCategory.setText(documentSnapshot2.getString("category"));
                        adminAddress.setText(documentSnapshot2.getString("address"));
                        adminContactNum.setText(documentSnapshot2.getString("contact_num"));
                        adminPassword.setText(documentSnapshot2.getString("password"));
                   //   adminId.setText(documentSnapshot2.getString("admin_sign_in_id"));
                    } else {
                        // User not found in Firestore
                        Toast.makeText(EditAdmin.this, "", Toast.LENGTH_SHORT).show();
                    }
                });

        buttonSaveEditAdmin = findViewById(R.id.buttonSaveEditAdmin);
        buttonSaveEditAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("admin_sign_in").document();

                //stores the information to the newly created variables
                String firstnameInput = adminFirstName.getText().toString();
                String lastnameInput = adminLastName.getText().toString();
                String categoryInput = adminCategory.getText().toString();
                String addressInput = adminAddress.getText().toString();
                String contactNumInput = adminContactNum.getText().toString();
                String passInput = adminPassword.getText().toString();

                addUser(firstnameInput, lastnameInput, categoryInput,
                        addressInput, contactNumInput, passInput);
//                Intent intent = new Intent(getApplication(), SettingsActivity.class);
//                startActivity(intent);
            }
        });
    }
    public void addUser(String firstnameInput, String lastnameInput, String categoryInput,
                        String addressInput, String contactNumInput, String passInput) {
        Map<String, Object> signUp = new HashMap<>();
        signUp.put("first_name", firstnameInput);
        signUp.put("last_name", lastnameInput);
        signUp.put("address", addressInput);
        signUp.put("category", categoryInput);
        signUp.put("contact_num", contactNumInput);
        signUp.put("password", passInput);

        db.collection("admin_sign_in").document(TemporaryDB.email)
                .update(signUp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + TemporaryDB.email);
                Toast.makeText(EditAdmin.this, "Successfully Edited"
                        , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), AdminProfileActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditAdmin.this, "Error editing document" + e
                        , Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Error adding document", e);

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

        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingsAdmin.class);
                startActivity(intent);
            }
        });
    }
}