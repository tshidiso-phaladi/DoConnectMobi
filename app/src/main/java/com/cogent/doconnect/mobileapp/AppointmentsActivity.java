package com.cogent.doconnect.mobileapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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

public class AppointmentsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointmentsactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new LoadAppointments().execute();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appointments, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
class LoadAppointments extends AsyncTask<String, String, String>
{
    Appointment apps;
    public LoadAppointments()
    {

    }
    @Override
    protected void onPreExecute() {
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
        apps = new Appointment();

        apps = getPatientAppointments();
        return null;
    }
    protected void onPostExecute(String file_url)
    {
        // dismiss the dialog after getting all products
        //pDialog.dismiss();
        // updating UI from Background Thread
    }
    private Appointment getPatientAppointments()
    {
        String result = "";
        Appointment appointment = new Appointment();
        try
        {
            result = RetrieveAppointment();
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
                appointment.SetAppointment_ID(json_data.getInt("ID"));
                appointment.SetAppointments_App_Status(json_data.getBoolean("Appointments_App_Status"));
                appointment.SetAppointments_Date_Time(json_data.getString("Appointments_Date_Time"));
                appointment.SetAppointments_Details(json_data.getString("Appointments_Details"));
            }
            catch(JSONException e)
            {
                Log.e("log_tag","Error Parsing Data "+e.toString());
            }
        }
        return appointment;
    }

    private String RetrieveAppointment()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {


            DefaultHttpClient httpClient=new DefaultHttpClient();

            //Connect to the server
            HttpPost httpPost = new HttpPost("http://10.1.2.190/Service/InfoServ.asmx/GetAppointmentForPatient?wsdl");
            //http://localhost/Service/InfoServ.asmx?op=GetPatientProfile

            List<NameValuePair> params = new ArrayList<NameValuePair>(1);
            params.add(new BasicNameValuePair("id", ""));
            //params.add(new BasicNameValuePair("param-2", "Hello!"));
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

