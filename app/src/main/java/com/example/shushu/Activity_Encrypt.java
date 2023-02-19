package com.example.shushu;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import javax.crypto.SecretKey;

public class Activity_Encrypt extends AppCompatActivity {

    TextInputEditText encrypt_EDTXT_text;
    MaterialButton encrypt_BTN_encrypt;
    TextInputEditText encrypt_EDTXT_textKey;
    MaterialButton encrypt_BTN_copyKey;
    TextInputEditText encrypt_EDTXT_textOutput;
    MaterialButton encrypt_BTN_copyOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        findViews();
        setClickListeners();
    }

    private void setClickListeners() {
        encrypt_BTN_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecretKey key = generateKey();
                displaySecretKeyToUserAsString(key);
                encryptAndDisplayOutput(key);
            }
        });

        encrypt_BTN_copyKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("blabla", encrypt_EDTXT_textKey.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });

        encrypt_BTN_copyOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("blabla", encrypt_EDTXT_textOutput.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    private void encryptAndDisplayOutput(SecretKey key) {
        try {
            encrypt_EDTXT_textOutput.setText(CryptoUtils.encrypt(encrypt_EDTXT_text.getText().toString(), key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displaySecretKeyToUserAsString(SecretKey key) {
        String stringKey = CryptoUtils.convertSecretKeyToString(key);
        encrypt_EDTXT_textKey.setText(stringKey);
    }

    private SecretKey generateKey() {
        SecretKey key = null;
        try {
            key = CryptoUtils.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }

    private void findViews() {
        encrypt_EDTXT_text = findViewById(R.id.encrypt_EDTXT_text);
        encrypt_BTN_encrypt = findViewById(R.id.encrypt_BTN_encrypt);
        encrypt_EDTXT_textKey = findViewById(R.id.encrypt_EDTXT_textKey);
        encrypt_BTN_copyKey = findViewById(R.id.encrypt_BTN_copyKey);
        encrypt_EDTXT_textOutput = findViewById(R.id.encrypt_EDTXT_textOutput);
        encrypt_BTN_copyOutput = findViewById(R.id.encrypt_BTN_copyOutput);

    }
}
