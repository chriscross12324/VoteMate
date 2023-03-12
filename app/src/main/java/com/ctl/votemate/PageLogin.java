package com.ctl.votemate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_login);

        //Declare UI Elements
        EditText inputEmail = findViewById(R.id.emailInputText);
        EditText inputPassword = findViewById(R.id.passwordInputText);
        CardView buttonBack = findViewById(R.id.buttonBack);
        CardView buttonContinue = findViewById(R.id.buttonContinue);

        buttonContinue.setOnClickListener(v -> {
            try {
                if (isValidEmail(inputEmail.getText().toString().trim()) && inputPassword.getText().length() >= 1) {
                    GlobalVariables.globalAuth.signInWithEmailAndPassword(
                            inputEmail.getText().toString().trim(),
                            inputPassword.getText().toString()
                    ).addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            //Sign in Successful; Re-route user to Voting Page
                            Log.d("MSG", "signInSuccessful");
                        }
                    }).addOnFailureListener(e -> {
                        //Sign in Unsuccessful; Display error dialog
                        Log.e("MSG", "signInError: " + e.getMessage());
                        displayErrorDialog(e.getMessage());
                    });
                } else {
                    displayErrorDialog("The Email Address or Password are invalid, please correct any mistakes");
                }
            } catch (Exception e) {
                displayErrorDialog(e.getMessage());
            }
        });


        buttonBack.setOnClickListener(n -> {
            finish();
        });
    }

    private void displayErrorDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg).setNeutralButton("Ok", (dialog, which) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return ((Matcher) matcher).matches();
    }
}