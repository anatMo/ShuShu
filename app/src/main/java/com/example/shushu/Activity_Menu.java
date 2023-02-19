package com.example.shushu;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class Activity_Menu extends AppCompatActivity {
    MaterialButton menu_BTN_encrypt;
    MaterialButton menu_BTN_decrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViews();
        setClickListeners();
    }

    private void setClickListeners() {
        menu_BTN_encrypt.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Encrypt.class);
            startActivity(intent);
        });

        menu_BTN_decrypt.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Decrypt.class);
            startActivity(intent);
        });
    }

    private void findViews() {
        menu_BTN_encrypt = findViewById(R.id.menu_BTN_encrypt);
        menu_BTN_decrypt = findViewById(R.id.menu_BTN_decrypt);
    }
}
