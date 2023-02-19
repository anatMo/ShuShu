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

public class Activity_Decrypt extends AppCompatActivity {
    TextInputEditText decrypt_EDTXT_text;
    TextInputEditText decrypt_EDTXT_key;
    MaterialButton decrypt_BTN_decrypt;
    TextInputEditText decrypt_EDTXT_textOutput;
    MaterialButton decrypt_BTN_copyOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        findViews();
        setClickListeners();

    }

    private void setClickListeners() {
        decrypt_BTN_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SecretKey key = getSecretKey();
                decryptAndDisplayOutput(key);
            }
        });

        decrypt_BTN_copyOutput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("blabla", decrypt_EDTXT_textOutput.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    private void decryptAndDisplayOutput(SecretKey key) {
        try {
            decrypt_EDTXT_textOutput.setText(CryptoUtils.decrypt(decrypt_EDTXT_text.getText().toString(), key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SecretKey getSecretKey() {
        return CryptoUtils.convertStringToSecretKey(decrypt_EDTXT_key.getText().toString());
    }

    private void findViews() {
        decrypt_EDTXT_text = findViewById(R.id.decrypt_EDTXT_text);
        decrypt_EDTXT_key = findViewById(R.id.decrypt_EDTXT_key);
        decrypt_BTN_decrypt = findViewById(R.id.decrypt_BTN_decrypt);
        decrypt_EDTXT_textOutput = findViewById(R.id.decrypt_EDTXT_textOutput);
        decrypt_BTN_copyOutput = findViewById(R.id.decrypt_BTN_copyOutput);
    }
}
