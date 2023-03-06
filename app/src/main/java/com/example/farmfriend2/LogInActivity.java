package com.example.farmfriend2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LogInActivity extends AppCompatActivity {
    private Button buttonLogIn;
    private EditText email, password;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        TextView email =(TextView) findViewById(R.id.email);
        TextView password =(TextView) findViewById(R.id.password);

        buttonLogIn = findViewById(R.id.buttonLogIn);

        db = FirebaseFirestore.getInstance();

        buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets the collection reference of pets profile
                CollectionReference collectionRef = db.collection("sign_in_farmer");
                Query query = collectionRef.whereEqualTo("email", ""+email);
                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            String newEmail = document.get("email").toString();
                            String newPassword = document.get("password").toString();

                            if(newEmail.equals(email.toString()) && newPassword.equals(password.toString())) {
                                Toast.makeText(LogInActivity.this, "", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplication(), FarmerProfileActivity.class);
                                startActivity(intent);
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LogInActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
