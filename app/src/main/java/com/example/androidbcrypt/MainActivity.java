package com.example.androidbcrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    Button hashPassword, verifyPassword;
    TextView hashResult, verifyResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hashPassword = findViewById(R.id.btnHash);
        hashResult = findViewById(R.id.hashResult);
        verifyPassword = findViewById(R.id.btnVerify);
        verifyResult = findViewById(R.id.verifyResult);


        hashPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = "1234";
                //char[] bcryptChars = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToChar(6, password.toCharArray());
                //String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
                String bcryptHashString = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, password.toCharArray());

                //hashResult.setText(new String(bcryptChars));
                hashResult.setText(bcryptHashString);

                //BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptChars);
                //BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
                //verifyResult.setText("pw verified: " + result.verified);
            }
        });

        verifyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = "1234";
                CharSequence bcryptHashString = hashResult.getText();
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
                verifyResult.setText("pw verified: " + result.verified);

            }
        });
    }
}