package com.ctl.votemate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
private LinearLayout buttonLoginPage;
private LinearLayout buttonRegisterPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLoginPage = findViewById(R.id.buttonLogin);
        buttonRegisterPage = findViewById(R.id.buttonRegister);

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