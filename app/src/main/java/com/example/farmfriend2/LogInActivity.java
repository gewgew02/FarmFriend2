    package com.example.farmfriend2;

    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;
    import android.util.Log;
    import android.widget.Button;
    import android.content.Intent;
    import android.text.TextUtils;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.OnFailureListener;
    import com.google.android.gms.tasks.OnSuccessListener;
    import com.google.firebase.firestore.CollectionReference;
    import com.google.firebase.firestore.DocumentSnapshot;
    import com.google.firebase.firestore.FirebaseFirestore;
    import com.google.firebase.firestore.Query;
    import com.google.firebase.firestore.QueryDocumentSnapshot;
    import com.google.firebase.firestore.QuerySnapshot;

    import org.checkerframework.checker.nullness.qual.NonNull;

    import java.util.Objects;

    public class LogInActivity extends AppCompatActivity {
    private Button buttonLogIn;
    private EditText email, password;
    final String TAG = "FIRESTORE";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();
                String passInput = password.getText().toString();

                db.collection("admin_sign_in")
                        .whereEqualTo("email", emailInput)
                        //.whereEqualTo("email", emailLogin)
                        .get().addOnSuccessListener(queryDocumentSnapshots -> {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                                String userPassword = documentSnapshot.getString("password");

                                // Check if the password entered by the user matches the password in Firestore
                                if (Objects.equals(userPassword, String.valueOf(passInput))) {
                                    // User logged in successfully
                                    Intent intent = new Intent(getApplication(), AdminProfileActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(LogInActivity.this, "Welcome Admin!"
                                            , Toast.LENGTH_SHORT).show();
                                } else {
                                    // Incorrect password
                                    Toast.makeText(LogInActivity.this, "Invalid Password"
                                            , Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // User not found in Firestore
                                Toast.makeText(LogInActivity.this, "Check Inputs!"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LogInActivity.this, "Error" + e
                                        , Toast.LENGTH_SHORT).show();
                                Log.w("FIRESTORE", "Error finding user", e);
                            }
                        });
//
//                db.collection("farmer_sign_in")
//                        .whereEqualTo("farmerEmail", emailInput)
//                        //.whereEqualTo("email", emailLogin)
//                        .get().addOnSuccessListener(queryDocumentSnapshots -> {
//                            if (!queryDocumentSnapshots.isEmpty()) {
//                                DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
//                                String userPassword = documentSnapshot.getString("farmerPassword");
//
//                                // Check if the password entered by the user matches the password in Firestore
//                                if (Objects.equals(userPassword, String.valueOf(passInput))) {
//                                    // User logged in successfully
//                                    Intent intent = new Intent(getApplication(), FarmerProfileActivity.class);
//                                    startActivity(intent);
//                                    Toast.makeText(LogInActivity.this, "Welcome Farmer!"
//                                            , Toast.LENGTH_SHORT).show();
//                                } else {
//                                    // Incorrect password
//                                    Toast.makeText(LogInActivity.this, "Invalid Password"
//                                            , Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                // User not found in Firestore
//                                Toast.makeText(LogInActivity.this, "Check Inputs!"
//                                        , Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                db.collection("LGU_sign_in")
//                        .whereEqualTo("LGUEmail", emailInput)
//                        //.whereEqualTo("email", emailLogin)
//                        .get().addOnSuccessListener(queryDocumentSnapshots -> {
//                            if (!queryDocumentSnapshots.isEmpty()) {
//                                DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
//                                String userPassword = documentSnapshot.getString("LGUPassword");
//
//                                // Check if the password entered by the user matches the password in Firestore
//                                if (Objects.equals(userPassword, String.valueOf(passInput))) {
//                                    // User logged in successfully
//                                    Intent intent = new Intent(getApplication(), LGUProfileActivity.class);
//                                    startActivity(intent);
//                                    Toast.makeText(LogInActivity.this, "Welcome LGU!"
//                                            , Toast.LENGTH_SHORT).show();
//                                } else {
//                                    // Incorrect password
//                                    Toast.makeText(LogInActivity.this, "Invalid Password"
//                                            , Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                // User not found in Firestore
//                                Toast.makeText(LogInActivity.this, "Check Inputs!"
//                                        , Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });
    }
    }