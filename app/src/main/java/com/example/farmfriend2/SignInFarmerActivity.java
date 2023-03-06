package com.example.farmfriend2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignInFarmerActivity extends AppCompatActivity {
    private Button buttonSignIn;
//    private EditText farmerFirstName, farmerLastName, farmerId, farmerCategory, farmerAddress, farmerContactNum, farmerEmail, farmerPassword;
//    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_farmer);
        //Find the ID every Variable
//        farmerFirstName = findViewById(R.id.farmerFirstName);
//        farmerLastName = findViewById(R.id.farmerLastName);
//        farmerId = findViewById(R.id.farmerId);
//        farmerCategory = findViewById(R.id.farmerCategory);
//        farmerAddress = findViewById(R.id.farmerAddress);
//        farmerContactNum = findViewById(R.id.farmerContactNum);
//        farmerEmail = findViewById(R.id.farmerEmail);
//        farmerPassword = findViewById(R.id.farmerPassword);
//
//        db = FirebaseFirestore.getInstance();

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
  //              DocumentReference docRef = db.collection("farmer_sign_in").document();

//               stores the information to the newly created variables
//               String email = farmerEmail.getText().toString();

//                Map<String, Object> signUp = new HashMap<>();
//                signUp.put("farmer_sign_in_id", farmerEmail.getText().toString());
//                signUp.put("first_name", farmerFirstName.getText().toString());
//                signUp.put("last_name", farmerLastName.getText().toString());
//                signUp.put("address", farmerAddress.getText().toString());
//                signUp.put("category", farmerCategory.getText().toString());
//                signUp.put("contact_num", farmerContactNum.getText().toString());
//                signUp.put("password", farmerPassword.getText().toString());
//                signUp.put("email", farmerEmail.getText().toString());

//                db.collection("farmer_sign_in").document("" + farmerEmail.getText().toString()).set(signUp).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//
//                        if(task.isSuccessful())
//                        {
//                            Intent intent = new Intent(getApplication(), FarmerProfileActivity.class);
//                            startActivity(intent);
//                            Toast.makeText(SignInFarmerActivity.this, "Successful Sign Up", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(SignInFarmerActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(SignInFarmerActivity.this, "Sign Up Failed" +e, Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });
    }
}