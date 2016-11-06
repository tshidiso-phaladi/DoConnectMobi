package com.cogent.doconnect.mobileapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtUsername;
    EditText txtPassword;
    Button btnRegister;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btnLogin = (Button) findViewById(R.id.login);
        txtUsername = (EditText) findViewById(R.id.userName);
        txtPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {
                    String a = txtUsername.getText().toString();
                    String b = txtPassword.getText().toString();
                    GetMethod test = (GetMethod) new GetMethod(a, b).execute();
                    if (test.us == txtUsername.getText().toString() && test.pas == txtPassword.getText().toString())
                    {
                        Intent register = new Intent("android.intent.action.SIGNUPACTIVITY");
                        startActivity(register);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
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
class GetMethod extends AsyncTask<String, String, String>
{
    User user;
    String us = "";
    String pas = "";
    public  GetMethod(String username, String password)
    {
        us = username;
        pas = password;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //pDialog = new ProgressDialog(PatientProfileActivity.this);
        //pDialog.setMessage("Loading Profile ...");
        //pDialog.setIndeterminate(false);
        //pDialog.setCancelable(false);
        //pDialog.show();
    }

    protected String doInBackground(String... args)
    {
        user = new User();
        try
        {
            user = getUSer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private User getUSer()
    {
        String result = "";
        User user = new User();

        try
        {
            result = RetrieveUser();
        }
        catch (Exception e)
        {
            result = "false";
        }

        if(result.charAt(0) == '{')
        {
            try
            {
                JSONObject json_data = new JSONObject(result);
                user.SetID(json_data.getInt("ID"));
            }
            catch(JSONException e)
            {
                Log.e("log_tag", "Error Parsing Data "+e.toString());
            }
        }
        return user;
    }

    private String RetrieveUser()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://192.168.43.145/Service/InfoServ.asmx/Login?wsdl");

            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("email", "Sammy@gmail.com"));
            params.add(new BasicNameValuePair("password", "password"));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream stream = httpEntity.getContent();

            String result = convertStreamToString(stream);
            return result;

        }
        catch (Exception e)
        {
            return  "Could Not Connect to Server \n"+e.toString();
        }
    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try
        {
            while((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    protected void onPostExecute(String file_url)
    {

    }
}



