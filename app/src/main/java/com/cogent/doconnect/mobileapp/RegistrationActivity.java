package com.cogent.doconnect.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
             else
             {
                 if (!txtPassword.getText().equals(txtPasswordConfirm.getText()))
                 {
                     Snackbar.make(view, "Passwords do not match", Snackbar.LENGTH_LONG)
                             .setAction("Action", null).show();
                 }
                 else
                 {

                 }
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
class RegisterPatient extends AsyncTask<String, String, String>
{
    Patient patient;
    public RegisterPatient()
    {

    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        //pDialog = new ProgressDialog(PatientProfile.this);
        //pDialog.setMessage("Loading Profile ...");
        //pDialog.setIndeterminate(false);
        //pDialog.setCancelable(false);
        //pDialog.show();
    }
    protected String doInBackground(String... args)
    {
        // Building Parameters
        patient = new Patient();
        patient = createPatient();
        return null;
    }

    private Patient createPatient()
    {
        String result;
        com.cogent.doconnect.mobileapp.Patient patient = new com.cogent.doconnect.mobileapp.Patient();
        //boolean ans = false;
        try
        {
            result = SendPatientInfo();
        }
        catch(Exception e)
        {
            result = "false";
        }

        if(result.charAt(0) == '{' )
        {
            try
            {
                JSONObject json_data = new JSONObject(result);
                patient.SetFirstName(json_data.getString("FirstName"));
                patient.SetFirstName(json_data.getString("LastName"));
                patient.SetFirstName(json_data.getString("IdNumber"));
                patient.SetFirstName(json_data.getString("DOB"));
                patient.SetFirstName(json_data.getString("Gender"));
                patient.SetFirstName(json_data.getString("PostalAddress"));
                patient.SetFirstName(json_data.getString("Email"));
                patient.SetFirstName(json_data.getString("Password"));
            }
            catch(JSONException e)
            {
                Log.e("log_tag","Error Parsing Data "+e.toString());
            }
        }

        return patient;
    }

    protected void onPostExecute(String file_url)
    {
        // dismiss the dialog after getting all products
        //pDialog.dismiss();
        // updating UI from Background Thread
        //PatientID.setText(profile.GetFirstName());
    }

    private String SendPatientInfo()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {

            DefaultHttpClient httpClient=new DefaultHttpClient();

            //Connect to the server
            HttpPost httpPost = new HttpPost("http://10.1.2.190/Service/InfoServ.asmx/RegisterPatient?wsdl");
            //http://localhost/Service/InfoServ.asmx?op=GetPatientProfile

            List<NameValuePair> params = new ArrayList<NameValuePair>(1);
            params.add(new BasicNameValuePair("firstName", ""));
            params.add(new BasicNameValuePair("lastName", ""));
            params.add(new BasicNameValuePair("dob", ""));
            params.add(new BasicNameValuePair("idNumber", ""));
            params.add(new BasicNameValuePair("gender", ""));
            params.add(new BasicNameValuePair("postalAddress", ""));
            params.add(new BasicNameValuePair("email", ""));
            params.add(new BasicNameValuePair("password", ""));

            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            //Get the response
            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream stream = httpEntity.getContent();

            //Convert the stream to readable format
            String result = convertStreamToString(stream);

            return result;

        }
        catch(Exception e)
        {
            return "Could Not Connect to Server \n"+e.toString();
            //return "Could Not Connect to Server";
        }

    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;

        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
