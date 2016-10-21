package com.cogent.doconnect.mobileapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtSurname;
    EditText txtIdNumber;
    EditText txtDOB;
    RadioGroup rdbGroupGender;
    RadioButton rdbGender;
    EditText txtPostalAddress;
    EditText txtEmail;
    EditText txtPassword;
    EditText txtPasswordConfirm;
    Button btnSubmit;
    TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        txtName = (EditText)findViewById(R.id.input_name);
        txtSurname = (EditText)findViewById(R.id.input_surname);
        txtIdNumber = (EditText)findViewById(R.id.input_idNumber);
        txtDOB = (EditText)findViewById(R.id.input_DOB);
        rdbGroupGender = (RadioGroup) findViewById(R.id.GenderRadioGroup);
        rdbGender = (RadioButton)findViewById(rdbGroupGender.getCheckedRadioButtonId());
        txtPostalAddress = (EditText)findViewById(R.id.input_address);
        txtEmail = (EditText)findViewById(R.id.input_email);
        txtPassword = (EditText)findViewById(R.id.input_password);
        txtPasswordConfirm = (EditText)findViewById(R.id.input_passwordconfirm);
        btnSubmit = (Button)findViewById(R.id.btn_signup);
        loginLink = (TextView)findViewById(R.id.link_login);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
             if(txtName.getText().equals("") || txtSurname.getText().equals("") || txtIdNumber.getText().equals("") ||
                     txtDOB.getText().equals("") || txtPostalAddress.getText().equals("") || txtEmail.getText().equals("")
                     || txtPassword.getText().equals("") || txtPasswordConfirm.getText().equals(""))
             {
                 Snackbar.make(view, "Supply all fields", Snackbar.LENGTH_LONG)
                         .setAction("Action", null).show();
             }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent signIn = new Intent("android.intent.action.MAIN");
                startActivity(signIn);
            }
        });

    }
}
