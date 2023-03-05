package com.ctl.votemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declare UI Elements
        LinearLayout buttonLoginPage = findViewById(R.id.buttonLogin);
        LinearLayout buttonRegisterPage = findViewById(R.id.buttonRegister);

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