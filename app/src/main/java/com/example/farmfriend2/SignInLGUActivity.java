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

public class SignInLGUActivity extends AppCompatActivity {
    private Button buttonSignIn;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_in_lguactivity);
        //Find the ID every Variable
        EditText LGUFirstName = findViewById(R.id.LGUFirstName);
        EditText LGULastName = findViewById(R.id.LGULastName);
        EditText LGUId = findViewById(R.id.LGUId);
        EditText LGUCategory = findViewById(R.id.LGUCategory);
        EditText LGUAddress = findViewById(R.id.LGUAddress);
        EditText LGUContactNum = findViewById(R.id.LGUContactNum);
        EditText LGUEmail = findViewById(R.id.LGUEmail);
        EditText LGUPassword = findViewById(R.id.LGUPassword);

        db = FirebaseFirestore.getInstance();

        buttonSignIn = findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("lgu_sign_in").document();

                //stores the information to the newly created variables
                String firstnameInput = LGUFirstName.getText().toString();
                String lastnameInput = LGULastName.getText().toString();
                String idInput = LGUId.getText().toString();
                String categoryInput = LGUCategory.getText().toString();
                String addressInput = LGUAddress.getText().toString();
                String contactNumInput = LGUContactNum.getText().toString();
                String emailInput = LGUEmail.getText().toString();
                String passInput = LGUPassword.getText().toString();

                if(!firstnameInput.isEmpty() && !lastnameInput.isEmpty() && !idInput.isEmpty() && !categoryInput.isEmpty() &&
                        !addressInput.isEmpty() && !contactNumInput.isEmpty() && !emailInput.isEmpty() && !passInput.isEmpty()){
                    addUser(firstnameInput, lastnameInput, idInput, categoryInput,
                            addressInput, contactNumInput, emailInput, passInput);
                    Finish();
                } else{
                    Toast.makeText(SignInLGUActivity.this, "All fields required!"
                            , Toast.LENGTH_SHORT).show();
                }
            }

            public void addUser(String firstnameInput, String lastnameInput, String idInput, String categoryInput,
                                  String addressInput, String contactNumInput, String emailInput, String passInput) {
                Map<String, Object> signUp = new HashMap<>();
                signUp.put("LGU_sign_in_id", idInput);
                signUp.put("first_name", firstnameInput);
                signUp.put("last_name", lastnameInput);
                signUp.put("address", addressInput);
                signUp.put("category", categoryInput);
                signUp.put("contact_num", contactNumInput);
                signUp.put("password", passInput);
                signUp.put("email", emailInput);

                db.collection("LGU_sign_in").document(emailInput)
                        .set(signUp)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + emailInput);
                                Toast.makeText(SignInLGUActivity.this, "Successfully Added"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SignInLGUActivity.this, "Error adding document" + e
                                        , Toast.LENGTH_SHORT).show();
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
            public void Finish(){
                Intent intent = new Intent(getApplication(), LogInActivity.class);
                startActivity(intent);
                Toast.makeText(SignInLGUActivity.this, "Successfully Added"
                        , Toast.LENGTH_SHORT).show();
            }
        });

    }
}