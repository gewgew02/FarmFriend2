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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class SignInAdminActivity extends AppCompatActivity {
    private Button buttonSignIn;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in_admin);
        //Find the ID every Variable
        EditText adminFirstName = findViewById(R.id.adminFirstName);
        EditText adminLastName = findViewById(R.id.adminLastName);
        EditText adminId = findViewById(R.id.adminId);
        EditText adminCategory = findViewById(R.id.adminCategory);
        EditText adminAddress = findViewById(R.id.adminAddress);
        EditText adminContactNum = findViewById(R.id.adminContactNum);
        EditText adminEmail = findViewById(R.id.adminEmail);
        EditText adminPassword = findViewById(R.id.adminPassword);

        db = FirebaseFirestore.getInstance();

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("admin_sign_in").document();
                String firstnameInput = adminFirstName.getText().toString();
                String lastnameInput = adminLastName.getText().toString();
                String idInput = adminId.getText().toString();
                String categoryInput = adminCategory.getText().toString();
                String addressInput = adminAddress.getText().toString();
                String contactNumInput = adminContactNum.getText().toString();
                String emailInput = adminEmail.getText().toString();
                String passInput = adminPassword.getText().toString();

                if(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !idInput.isEmpty() && !categoryInput.isEmpty() &&
                        !addressInput.isEmpty() && !contactNumInput.isEmpty() && !emailInput.isEmpty() && !passInput.isEmpty()){
                    addUser(firstnameInput, lastnameInput, idInput, categoryInput,
                            addressInput, contactNumInput, emailInput, passInput);
                    Finish();
                } else{
                    Toast.makeText(SignInAdminActivity.this, "All fields required!"
                            , Toast.LENGTH_SHORT).show();
                }
            }

            public void addUser(String firstnameInput, String lastnameInput, String idInput, String categoryInput,
                                String addressInput, String contactNumInput, String emailInput, String passInput) {
                // Create a new user with a first and last name
                Map<String, Object> signUp = new HashMap<>();
                signUp.put("admin_sign_in_id", idInput);
                signUp.put("first_name", firstnameInput);
                signUp.put("last_name", lastnameInput);
                signUp.put("address", addressInput);
                signUp.put("category", categoryInput);
                signUp.put("contact_num", contactNumInput);
                signUp.put("password", passInput);
                signUp.put("email", emailInput);

                db.collection("admin_sign_in").document(emailInput)
                        .set(signUp)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + emailInput);
                                Toast.makeText(SignInAdminActivity.this, "Successfully Added"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignInAdminActivity.this, "Error adding document" + e
                                        , Toast.LENGTH_SHORT).show();
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
            public void Finish(){
                Intent intent = new Intent(getApplication(), LogInActivity.class);
                startActivity(intent);
                Toast.makeText(SignInAdminActivity.this, "Successfully Added"
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }
}