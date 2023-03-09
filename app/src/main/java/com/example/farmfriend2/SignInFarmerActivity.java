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

public class SignInFarmerActivity extends AppCompatActivity {
    private Button buttonSignIn;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in_farmer);
        //Find the ID every Variable
        EditText farmerFirstName = findViewById(R.id.farmerFirstName);
        EditText farmerLastName = findViewById(R.id.farmerLastName);
//        EditText farmerId = findViewById(R.id.farmerId);
        EditText farmerCategory = findViewById(R.id.farmerCategory);
        EditText farmerAddress = findViewById(R.id.farmerAddress);
        EditText farmerContactNum = findViewById(R.id.farmerContactNum);
        EditText farmerEmail = findViewById(R.id.farmerEmail);
        EditText farmerPassword = findViewById(R.id.farmerPassword);

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("farmer_sign_in").document();

                //stores the information to the newly created variables
                String firstnameInput = farmerFirstName.getText().toString();
                String lastnameInput = farmerLastName.getText().toString();
//                String idInput = farmerId.getText().toString();
                String categoryInput = farmerCategory.getText().toString();
                String addressInput = farmerAddress.getText().toString();
                String contactNumInput = farmerContactNum.getText().toString();
                String farmerEmailInput = farmerEmail.getText().toString();
                String passInput = farmerPassword.getText().toString();

                if(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !categoryInput.isEmpty() &&
                        !addressInput.isEmpty() && !contactNumInput.isEmpty() && !farmerEmailInput.isEmpty() && !passInput.isEmpty()){
                    addUser(firstnameInput, lastnameInput, categoryInput, addressInput, contactNumInput, farmerEmailInput, passInput);
                    Finish();
                } else{
                    Toast.makeText(SignInFarmerActivity.this, "All fields required!"
                            , Toast.LENGTH_SHORT).show();
                }
            }
            public void addUser(String firstnameInput, String lastnameInput, String categoryInput,
                                String addressInput, String contactNumInput, String emailInput, String passInput) {
                Map<String, Object> signUp = new HashMap<>();
//                signUp.put("farmer_sign_in_id", idInput);
                signUp.put("first_name", firstnameInput);
                signUp.put("last_name", lastnameInput);
                signUp.put("address", addressInput);
                signUp.put("category", categoryInput);
                signUp.put("contact_num", contactNumInput);
                signUp.put("password", passInput);
                signUp.put("email", emailInput);

                db.collection("farmer_sign_in").document(emailInput)
                        .set(signUp)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + emailInput);
                                Toast.makeText(SignInFarmerActivity.this, "Successfully Added"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignInFarmerActivity.this, "Error adding document" + e
                                        , Toast.LENGTH_SHORT).show();
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
            public void Finish(){
                Intent intent = new Intent(getApplication(), LoginFarmer.class);
                startActivity(intent);
            }
        });
    }
}