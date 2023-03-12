package com.ctl.votemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class PageRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_register);
        //Firebase
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cRef = db.collection("userInfo");

        //Declare UI Elements
        EditText inputEmail = findViewById(R.id.emailInputText);
        EditText inputPassword = findViewById(R.id.passwordInputText);
        EditText inputInvite = findViewById(R.id.inviteInputText);
        CardView buttonBack = findViewById(R.id.buttonBack);
        CardView buttonContinue = findViewById(R.id.buttonContinue);

        buttonContinue.setOnClickListener(v -> {
            String inviteCode = inputInvite.getText().toString().trim();
            Query findInviteQuery = cRef.whereEqualTo("inviteCode", inviteCode);

            findInviteQuery.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshot = task.getResult();
                    if (!snapshot.isEmpty()) {
                        DocumentSnapshot documentSnapshot = snapshot.getDocuments().get(0);
                        Map<String, Object> data = documentSnapshot.getData();
                        boolean isClaimed = Boolean.TRUE.equals(documentSnapshot.getBoolean("isClaimed"));

                        if (isClaimed) {
                            PageLogin.displayErrorDialog(this, "Can't Create Account");
                        } else {
                            GlobalVariables.globalAuth.createUserWithEmailAndPassword(
                                    inputEmail.getText().toString().trim(),
                                    inputPassword.getText().toString().trim())
                                    .addOnCompleteListener(this, task1 -> {
                                        if (task1.isSuccessful()) {
                                            PageLogin.displayErrorDialog(this, "Account Created");
                                            cRef.document(documentSnapshot.getId())
                                                    .update("isClaimed", true)
                                                    .addOnCompleteListener(task2 -> {
                                                        PageLogin.displayErrorDialog(this, "Value Updated on Server");
                                            }).addOnFailureListener(e -> {
                                                        PageLogin.displayErrorDialog(this, "Failed to update value");
                                            });
                                        }
                            })
                                    .addOnFailureListener(e -> {
                                        PageLogin.displayErrorDialog(this, e.getMessage());
                                    });
                            PageLogin.displayErrorDialog(this, "Can Create Account");
                        }
                    } else {
                        PageLogin.displayErrorDialog(this, "Invalid Invite Code");
                    }
                }
            });
        });

        buttonBack.setOnClickListener(n -> finish());
    }
}