package com.example.androidbcrypt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    Button hashPassword, verifyPassword, enterPassword;
    TextView hashResult, verifyResult, enteredPassword;
    String enteredPasswordString = "";

    private PasswordDialog passwordDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hashPassword = findViewById(R.id.btnHash);
        hashResult = findViewById(R.id.hashResult);
        verifyPassword = findViewById(R.id.btnVerify);
        verifyResult = findViewById(R.id.verifyResult);
        enterPassword = findViewById(R.id.btnPassword);
        enteredPassword = findViewById(R.id.password);

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

        enterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String message = "Enter a password to hash";
                //showPasswordEnter(message, getApplicationContext());

                passwordDialog = new PasswordDialog(v.getContext());
                passwordDialog.show();

            }
        });

        /*
new AlertDialog.Builder(Main.this)
    .setTitle("Update Status")
    .setMessage(message)
    .setView(input)
    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            Editable value = input.getText();
        }
    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int whichButton) {
            // Do nothing.
        }
    }).show();
         */
    }

    void showPasswordEnter(String message, Context context) {


    }
}