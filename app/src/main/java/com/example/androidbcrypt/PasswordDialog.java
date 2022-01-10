package com.example.androidbcrypt;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by AaronPasi on 2017/9/16.
 */

public class PasswordDialog extends AlertDialog implements View.OnClickListener {

    EditText mEtPasswd;
    Button mBtnCancel, mBtnConnect;
    Context mContext;

    // here the entered password is stored
    TextView enteredPassword;

    public PasswordDialog(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        mEtPasswd = (EditText) findViewById(R.id.et_passwd);

        enteredPassword = (TextView) findViewById(R.id.edtPassword);

        //Ensure that EditText can pop up the keyboard
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        this.setCancelable(false);

        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener(this);

        mBtnConnect = (Button) findViewById(R.id.btn_connect);
        mBtnConnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                this.dismiss();
                break;
            case R.id.btn_connect:
                if (TextUtils.isEmpty(mEtPasswd.getText())) {
                    Toast.makeText(mContext, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, mEtPasswd.getText().toString(), Toast.LENGTH_SHORT).show();
                    enteredPassword.setText(mEtPasswd.getText().toString());
                    this.dismiss();

                }
                break;
            default:
                break;

        }
    }
}