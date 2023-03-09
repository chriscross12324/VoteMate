package com.ctl.votemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

public class PageRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_register);

        CardView buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(n -> {
            finish();
        });
    }
}