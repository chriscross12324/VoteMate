package com.ctl.votemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GlobalVariables.globalAuth = FirebaseAuth.getInstance();
        GlobalVariables.globalUser = GlobalVariables.globalAuth.getCurrentUser();

        if (GlobalVariables.globalUser != null) {
            Intent pageLogin = new Intent(this,PageLogin.class);
            startActivity(pageLogin);
        } else {
            //Declare UI Elements
            MaterialCardView buttonLoginPage = findViewById(R.id.buttonLogin);
            MaterialCardView buttonRegisterPage = findViewById(R.id.buttonRegister);

            //Navigation Buttons
            buttonLoginPage.setOnClickListener(v -> {
                Intent pageLogin = new Intent(this,PageLogin.class);
                startActivity(pageLogin);
            });
            buttonRegisterPage.setOnClickListener(v ->{
                Intent pageRegister = new Intent(this, PageRegister.class);
                startActivity(pageRegister);
            });
        }
    }
}