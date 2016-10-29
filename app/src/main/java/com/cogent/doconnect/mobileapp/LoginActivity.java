package com.cogent.doconnect.mobileapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername;
    EditText txtPassword;
    Button btnRegister;

    Connection conn;
    String userName, pass, db, ip;
    Boolean isSuccess = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btnLogin = (Button) findViewById(R.id.login);
        txtUsername = (EditText) findViewById(R.id.userName);
        txtPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.register);

        ip = "doconnect.database.windows.net";
        db = "DoConnect";
        userName = "teamCogent";
        pass = "DoConnect1";

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
                    Snackbar.make(v, "Please username and password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {

                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent register = new Intent("android.intent.action.SIGNUPACTIVITY");
                startActivity(register);
            }
        });
    }
}
