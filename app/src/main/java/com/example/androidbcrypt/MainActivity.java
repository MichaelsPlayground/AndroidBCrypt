package com.example.androidbcrypt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.snackbar.Snackbar;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    Button hashPassword, verifyPassword, enterPassword;
    TextView hashResult, verifyResult;
    EditText enteredPassword;
    final int minimalPasswordLength = 1; // ### should be 8

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hashPassword = findViewById(R.id.btnHash);
        hashResult = findViewById(R.id.hashResult);
        verifyPassword = findViewById(R.id.btnVerify);
        verifyResult = findViewById(R.id.verifyResult);
        enterPassword = findViewById(R.id.btnPassword);
        enteredPassword = findViewById(R.id.edtPassword);

        hashPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the entered password as char[]
                int enteredPasswordLength = enteredPassword.length();
                char[] password = new char[enteredPasswordLength];
                enteredPassword.getText().getChars(0, enteredPasswordLength, password, 0);
                String bcryptHashString = BCrypt.with(BCrypt.Version.VERSION_2Y).hashToString(12, password);
                hashResult.setText(bcryptHashString);
            }
        });

        verifyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the entered password as char[]
                int enteredPasswordLength = enteredPassword.length();
                char[] password = new char[enteredPasswordLength];
                enteredPassword.getText().getChars(0, enteredPasswordLength, password, 0);
                CharSequence bcryptHashString = hashResult.getText();
                BCrypt.Result result = BCrypt.verifyer().verify(password, bcryptHashString);
                verifyResult.setText("pw verified: " + result.verified);
            }
        });

        enterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Programm Passwort eingeben");
                String message = "\nBitte geben Sie ein mindestens\n8-stelliges Passwort ein und drücken\nSie auf LADEN, um alle\nProgrammfunktionen nutzen\nzu können.";
                alertDialog.setMessage(message);
                final EditText oldPassphrase = new EditText(MainActivity.this);
                oldPassphrase.setBackground(ResourcesCompat.getDrawable(getResources(),R.drawable.round_rect_shape, null));
                oldPassphrase.setHint("  Passwort");
                oldPassphrase.setPadding(50, 20, 50, 20);
                LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                lp1.setMargins(36, 36, 36, 36);
                oldPassphrase.setLayoutParams(lp1);
                RelativeLayout container = new RelativeLayout(MainActivity.this);
                RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                container.setLayoutParams(rlParams);
                container.addView(oldPassphrase);
                alertDialog.setView(container);
                alertDialog.setPositiveButton("eingeben", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int oldPassphraseLength = oldPassphrase.length();
                        char[] oldPassword = new char[oldPassphraseLength];
                        oldPassphrase.getText().getChars(0, oldPassphraseLength, oldPassword, 0);
                        // test on password length
                        if (oldPassphraseLength < minimalPasswordLength) {
                            Snackbar snackbar = Snackbar.make(v, "Das Passwort ist zu kurz", Snackbar.LENGTH_LONG);
                            snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.red));
                            snackbar.show();
                            return;
                        }
                        enteredPassword.setText(new String(oldPassword)); // don't print as string
                    }
                });
                alertDialog.setNegativeButton("abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar snackbar = Snackbar.make(v, "Passwort Eingabe abgebrochen", Snackbar.LENGTH_LONG);
                        snackbar.setBackgroundTint(ContextCompat.getColor(MainActivity.this, R.color.red));
                        snackbar.show();
                    }
                });
                alertDialog.show();
            }
        });
    }
}