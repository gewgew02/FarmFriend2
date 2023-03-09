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

public class EditLGU extends AppCompatActivity {
    private Button buttonSettings, buttonLogout, buttonSaveEditLGU;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lgu);
        EditText LGUFirstName = findViewById(R.id.LGUFirstName);
        EditText LGULastName = findViewById(R.id.LGULastName);
        EditText LGUCategory = findViewById(R.id.LGUCategory);
        EditText LGUAddress = findViewById(R.id.LGUAddress);
        EditText LGUContactNum = findViewById(R.id.LGUContactNum);
        EditText LGUPassword = findViewById(R.id.LGUPassword);
//        TextView LGUId = findViewById(R.id.LGUId);

        db.collection("LGU_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                        LGUFirstName.setText(documentSnapshot2.getString("first_name"));
                        LGULastName.setText(documentSnapshot2.getString("last_name"));
                        LGUCategory.setText(documentSnapshot2.getString("category"));
                        LGUAddress.setText(documentSnapshot2.getString("address"));
                        LGUContactNum.setText(documentSnapshot2.getString("contact_num"));
                        LGUPassword.setText(documentSnapshot2.getString("password"));
//                        LGUId.setText(documentSnapshot2.getString("LGU_sign_in_id"));
                    } else {
                        // User not found in Firestore
                        Toast.makeText(EditLGU.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
        buttonSaveEditLGU = findViewById(R.id.buttonSaveEditLGU);
        buttonSaveEditLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("LGU_sign_in").document();

                //stores the information to the newly created variables
                String firstnameInput = LGUFirstName.getText().toString();
                String lastnameInput = LGULastName.getText().toString();
                String categoryInput = LGUCategory.getText().toString();
                String addressInput = LGUAddress.getText().toString();
                String contactNumInput = LGUContactNum.getText().toString();
                String passInput = LGUPassword.getText().toString();

                addUser(firstnameInput, lastnameInput, categoryInput,
                        addressInput, contactNumInput, passInput);
//                Intent intent = new Intent(getApplication(), SettingsActivity.class);
//                startActivity(intent);
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
                Intent intent = new Intent(getApplication(), SettingsFarmer.class);
                startActivity(intent);
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

        db.collection("LGU_sign_in").document(TemporaryDB.email)
                .update(signUp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + TemporaryDB.email);
                        Toast.makeText(EditLGU.this, "Successfully Edited"
                                , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), LGUProfileActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditLGU.this, "Error editing document" + e
                                , Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Error adding document", e);

                    }
                });
    }
}