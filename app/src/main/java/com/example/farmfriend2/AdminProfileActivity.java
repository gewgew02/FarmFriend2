package com.example.farmfriend2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminProfileActivity extends AppCompatActivity {
    private Button buttonSettings, buttonCheckWeather, buttonSuggestedCropstoPlant, buttonViewUserFarmer, buttonViewUsersLGU, buttonLogout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private ImageView imageViewAdmin1;
//    ProgressDialog progressDialog;
//    Uri imageuri;
//    FirebaseStorage imgStorage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView adminFirstName = findViewById(R.id.textViewName);
        TextView adminAddress = findViewById(R.id.textViewAddress);
        TextView adminCategory = findViewById(R.id.textViewCategory);
        TextView adminContact = findViewById(R.id.textViewAdminContact);

        db.collection("admin_sign_in")
                .whereEqualTo("email", TemporaryDB.email)
                //.whereEqualTo("email", emailLogin)
                .get().addOnSuccessListener(queryDocumentSnapshots2 -> {
                    if (!queryDocumentSnapshots2.isEmpty()) {
                        DocumentSnapshot documentSnapshot2 = queryDocumentSnapshots2.getDocuments().get(0);

                        adminFirstName.setText(documentSnapshot2.getString("first_name") + " " + documentSnapshot2.getString("last_name"));
                        adminAddress.setText(documentSnapshot2.getString("address"));
                        adminCategory.setText(documentSnapshot2.getString("category"));
                        adminContact.setText(documentSnapshot2.getString("contact_num"));

                        TemporaryDB.email = documentSnapshot2.getString("email");
                    } else {
                        // User not found in Firestore
                        Toast.makeText(AdminProfileActivity.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
//        imageViewAdmin1 = findViewById(R.id.imageViewAdmin);
//        imageViewAdmin1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mGetContent.launch("image/*");
//            }
//        });

        setContentView(R.layout.activity_admin_profile);

        buttonSettings = findViewById(R.id.buttonSettings);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SettingsAdmin.class);
                startActivity(intent);
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

        buttonCheckWeather = findViewById(R.id.buttonCheckWeather);
        buttonCheckWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CheckWeatherAdminActivity.class);
                startActivity(intent);
            }
        });

        buttonSuggestedCropstoPlant = findViewById(R.id.buttonSuggestedCropstoPlant);
        buttonSuggestedCropstoPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SuggestedCropsAdminActivity.class);
                startActivity(intent);
            }
        });

        buttonViewUserFarmer = findViewById(R.id.buttonViewUserFarmer);
        buttonViewUserFarmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), FarmerListsActivity.class);
                startActivity(intent);
            }
        });

        buttonViewUsersLGU = findViewById(R.id.buttonViewUsersLGU);
        buttonViewUsersLGU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LGUListsActivity.class);
                startActivity(intent);
            }
        });
    }
    //new activity for result upon choosing an image in the gallery
//    ActivityResultLauncher mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//        @Override
//        public void onActivityResult(Uri result) {
//            if(!(result == null)){
//                imageViewAdmin1.setImageURI(result);
//                //the result will now become the image
//                imageuri = result;
//                uploadInformation();
//            }
//        }
//    });

//    public void uploadInformation() {
//        //THIS will show the loading screen if the image is uploading into the storage database
//        if (!(imageuri == null)) {
//            progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading file...");
//            progressDialog.show();
//        }
//        //this is the location reference of the image from the cloud storage
//        StorageReference reference = imgStorage.getReference().child("AdminProfiles/" + TemporaryDB.email);
//
//        //StorageReference reference = fbStorage.getReference().child("Pet/" + firstName.getText().toString());
//        //this is to put the image reference into the image uri
//        reference.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if (task.isSuccessful()) {
//                    //if the loading screen is showing when the image has been put into the Firebase Storage
//                    if (progressDialog.isShowing()) {
//                        //the loading screen will be close
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(AdminProfileActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
//                } else {
//                    //this message will show if there is an error on image upload
//                    Toast.makeText(AdminProfileActivity.this, "Error Image Upload", Toast.LENGTH_SHORT).show();
//                    //this is to close the loading screen
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                }
//            }
//        });
//    }
}