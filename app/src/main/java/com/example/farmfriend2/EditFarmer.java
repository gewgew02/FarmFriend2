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

public class EditFarmer extends AppCompatActivity {
    private Button buttonSettings, buttonLogout, buttonSaveEditFarmer;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_farmer);

        EditText farmerFirstName = findViewById(R.id.farmerFirstName);
        EditText farmerLastName = findViewById(R.id.farmerLastName);
        EditText farmerCategory = findViewById(R.id.farmerCategory);
        EditText farmerAddress = findViewById(R.id.farmerAddress);
        EditText farmerContactNum = findViewById(R.id.farmerContactNum);
        EditText farmerPassword = findViewById(R.id.farmerPassword);
        TextView farmerId = findViewById(R.id.farmerId);

        db.collection("farmer_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                        farmerFirstName.setText(documentSnapshot2.getString("first_name"));
                        farmerLastName.setText(documentSnapshot2.getString("last_name"));
                        farmerCategory.setText(documentSnapshot2.getString("category"));
                        farmerAddress.setText(documentSnapshot2.getString("address"));
                        farmerContactNum.setText(documentSnapshot2.getString("contact_num"));
                        farmerPassword.setText(documentSnapshot2.getString("password"));
                        farmerId.setText(documentSnapshot2.getString("farmer_sign_in_id"));
                    } else {
                        // User not found in Firestore
                        Toast.makeText(EditFarmer.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
        buttonSaveEditFarmer = findViewById(R.id.buttonSaveEditFarmer);
        buttonSaveEditFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("farmer_sign_in").document();

                //stores the information to the newly created variables
                String firstnameInput = farmerFirstName.getText().toString();
                String lastnameInput = farmerLastName.getText().toString();
                String categoryInput = farmerCategory.getText().toString();
                String addressInput = farmerAddress.getText().toString();
                String contactNumInput = farmerContactNum.getText().toString();
                String passInput = farmerPassword.getText().toString();

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


        db.collection("farmer_sign_in").document(TemporaryDB.email)
                .update(signUp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + TemporaryDB.email);
                        Toast.makeText(EditFarmer.this, "Successfully Added"
                                , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), FarmerProfileActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditFarmer.this, "Error adding document" + e
                                , Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Error adding document", e);

                    }
                });
    }
}