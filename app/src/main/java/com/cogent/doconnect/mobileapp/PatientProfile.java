package com.cogent.doconnect.mobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.cogent.doconnect.mobileapp.PatientProfile.txtLastName;
import static com.cogent.doconnect.mobileapp.PatientProfile.txtPatientID;
import static com.cogent.doconnect.mobileapp.PatientProfile.pDialog;
import static com.cogent.doconnect.mobileapp.PatientProfile.txtFirstName;
import static com.cogent.doconnect.mobileapp.PatientProfile.txtGender;
import static com.cogent.doconnect.mobileapp.PatientProfile.txtDOB;
import static com.cogent.doconnect.mobileapp.PatientProfile.txtCell_Number;
import static com.cogent.doconnect.mobileapp.PatientProfile.txtID_Number;

public class PatientProfile extends AppCompatActivity {

    static EditText txtPatientID;
    static EditText txtFirstName;
    static EditText txtLastName;
    static EditText txtID_Number;
    static EditText txtGender;
    static EditText txtDOB;
    static EditText txtCell_Number;

    static Button btnLoad;
    static ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        txtPatientID = (EditText) findViewById(R.id.txt_Patient_ID);
        txtFirstName = (EditText) findViewById(R.id.txt_FirstName);
        txtLastName = (EditText) findViewById(R.id.txt_LastName);
        txtID_Number = (EditText) findViewById(R.id.txt_ID_Number);
        txtGender = (EditText) findViewById(R.id.txt_Gender);
        txtDOB = (EditText) findViewById(R.id.txt_DOB);
        txtCell_Number = (EditText) findViewById(R.id.txt_Cell_Number);
        btnLoad = (Button) findViewById(R.id.btn_LoadProfile);


        btnLoad.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub

                new LoadProfile(txtPatientID.getText().toString(),PatientProfile.this).execute();

            }
        });
    }



}
class LoadProfile extends AsyncTask<String, String, String> {

    Patient profile;
    String UserId;
    Context ConText;

    public LoadProfile(String userId, Context appContext)
    {
        UserId = userId;
        ConText = appContext;

    }

    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(ConText);
        pDialog.setMessage("Loading Profile ...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    /**
     *
     * */


    protected String doInBackground(String... args)
    {
        // Building Parameters
         profile = new Patient();

         profile = getPatientProfile();

        return null;
    }


    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String file_url)
    {
        // dismiss the dialog after getting all products
        pDialog.dismiss();
        // updating UI from Background Thread
        txtFirstName.setText(profile.GetFirstName());
        txtLastName.setText(profile.GetLastName());
        txtID_Number.setText(profile.GetID_Number());
        txtGender.setText(profile.GetGender());
        txtDOB.setText(profile.GetDOB());
        txtCell_Number.setText(profile.GetCell_Number());

    }
    private String RetrieveProfile()
    {

        try
        {

            DefaultHttpClient httpClient=new DefaultHttpClient();

            //Connect to the server
            HttpPost httpPost = new HttpPost("http://192.168.1.100/Service/InfoServ.asmx/GetPatientProfile?wsdl");
            //http://localhost/Service/InfoServ.asmx?op=GetPatientProfile

            List<NameValuePair> params = new ArrayList<NameValuePair>(1);
            params.add(new BasicNameValuePair("id", UserId));
            //params.add(new BasicNameValuePair("param-2", "Hello!"));
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            //Get the response
            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream stream = httpEntity.getContent();

            //Convert the stream to readable format
            String result= convertStreamToString(stream);

            return result;

        }
        catch(Exception e)
        {
            return "Could Not Connect to Server \n"+e.toString();
            //return "Could Not Connect to Server";
        }

    }
    private static String convertStreamToString(InputStream is)
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
    private com.cogent.doconnect.mobileapp.Patient getPatientProfile()
    {
        //String msg = "";
        String result = "";
        com.cogent.doconnect.mobileapp.Patient patient = new com.cogent.doconnect.mobileapp.Patient();
        //boolean ans = false;
        try
        {
            result = RetrieveProfile();
        }
        catch(Exception e)
        {
            result = "false";
        }

        if(result.charAt(0) == '{' )
        {
            try
            {
                //JSONArray jArray = new JSONArray(result);
                JSONObject json_data = new JSONObject(result);
                //JSONObject json_data = jArray.getJSONObject(0);
                patient.SetFirstName(json_data.getString("FirstName"));
                patient.SetLastName(json_data.getString("LastName"));
                patient.SetID_Number(json_data.getString("ID_Number"));
                patient.SetGender(json_data.getString("Gender"));
                patient.SetDOB(json_data.getString("DOB"));
                patient.SetCell_Number(json_data.getString("Cell_Number"));
                patient.SetFirstName(json_data.getString("FirstName"));

                //JSONArray jArray = json_data.getJSONArray("DateOfBirth");

//                try {
//                    //JSONObject oneObject = jArray.getJSONObject(0);
//                JSONObject oneObject = json_data.getJSONObject("DateOfBirth");
//                // Pulling items from the array
//                String dateStr = oneObject.getString("date");
//                //UserProfile.setAuthCompDOB(oneObject.getString("date"));
//                int place = dateStr.indexOf(' ');
//                String dateOnly = dateStr.substring(0, place);
//                UserProfile.setAuthCompDOB(dateOnly);
//            } catch (JSONException e) {
//                UserProfile.setAuthCompDOB("Cant find jack");
//            }


                //ans = true;
            }
            catch(JSONException e)
            {
                Log.e("log_tag","Error Parsing Data "+e.toString());
                //msg = ""+e.toString();
            }
        }

        return patient;
    }

}