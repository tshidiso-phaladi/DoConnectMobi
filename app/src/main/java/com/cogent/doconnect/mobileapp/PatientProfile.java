package com.cogent.doconnect.mobileapp;

import android.app.ProgressDialog;
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

import static com.cogent.doconnect.mobileapp.PatientProfile.PatientID;
import static com.cogent.doconnect.mobileapp.PatientProfile.pDialog;

public class PatientProfile extends AppCompatActivity {

    static EditText PatientID;
    static Button btnUpdate;
    static ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        PatientID = (EditText) findViewById(R.id.PatientIDeditText);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);


        btnUpdate.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub

                new LoadProfile().execute();

            }
        });
    }



}
class LoadProfile extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(PatientProfile.this);
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
        Patient profile = new Patient();

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
        PatientID.setText("");

    }
    private String RetrieveProfile()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {

            DefaultHttpClient httpClient=new DefaultHttpClient();

            String params =  "{}";

            //Connect to the server
            HttpPost httpPost = new HttpPost("192.168.43.145/Service/InfoServ.asmx?op=GetPatientProfile");
            //http://localhost/Service/InfoServ.asmx?op=GetPatientProfile

            List<NameValuePair> params = new ArrayList<NameValuePair>(2);
            params.add(new BasicNameValuePair("PatId", "2"));
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
                patient.SetFirstName(json_data.getString("CompAuthID"));
//                UserProfile.setAuthCompIDno(json_data.getString("IDnumber"));
//                UserProfile.setAuthCompFirstName(json_data.getString("FirstName"));
//                UserProfile.setAuthCompMiddleName(json_data.getString("MiddleName"));
//                UserProfile.setAuthCompLastName(json_data.getString("LastName"));
//                //UserProfile.setAuthCompDOB(json_data.getString("DateOfBirth"));
//                UserProfile.setAuthCompContactNumber(json_data.getString("ContactNumber"));
//                UserProfile.setAuthCompContactNumber2(json_data.getString("ContactNumber2"));

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