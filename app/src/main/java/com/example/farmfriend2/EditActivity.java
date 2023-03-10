package com.example.farmfriend2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class EditActivity extends AppCompatActivity {
    private Button buttonSettings, buttonLogout, buttonSaveEdit;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_edit);

        EditText LGUFirstName = findViewById(R.id.LGUFirstName);
        EditText LGULastName = findViewById(R.id.LGULastName);
        EditText LGUCategory = findViewById(R.id.LGUCategory);
        EditText LGUAddress = findViewById(R.id.LGUAddress);
        EditText LGUContactNum = findViewById(R.id.LGUContactNum);
        EditText LGUPassword = findViewById(R.id.LGUPassword);
        TextView LGUId = findViewById(R.id.LGUId);

        db.collection("admin_sign_in")
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
                        LGUId.setText(documentSnapshot2.getString("admin_sign_in_id"));
                    } else {
                        // User not found in Firestore
                        Toast.makeText(EditActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
        buttonSaveEdit = findViewById(R.id.buttonSaveEdit);
        buttonSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("admin_sign_in").document();

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
                .set(signUp)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + TemporaryDB.email);
                        Toast.makeText(EditActivity.this, "Successfully Added"
                                , Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditActivity.this, "Error adding document" + e
                                , Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

}