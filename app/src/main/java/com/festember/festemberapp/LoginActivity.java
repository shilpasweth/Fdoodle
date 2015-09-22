package com.festember.festemberapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.os.AsyncTask;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import com.festember.festemberapp.R;
public class LoginActivity extends Activity {

    private EditText username_text,password_text;
    String regid = new String();
    GoogleCloudMessaging gcm = null;
    private CheckBox checkBox;
    public String user_mail,password;
    public SharedPreferences prefs;
    public Button SignIn;
    public Button register;
    Read_write_file fileOps;

    public Intent i;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        username_text = (EditText)findViewById(R.id.username_text);
        password_text = (EditText)findViewById(R.id.password);
        checkBox = (CheckBox)findViewById(R.id.showPasswordCheckBox);
        SignIn = (Button)findViewById(R.id.signInButton);
        register=(Button)findViewById(R.id.RegisterButton);
        prefs = getSharedPreferences("LogInPrefs", 0);
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gnu.ttf");
        fileOps=new Read_write_file(this);
        username_text.setTypeface(typeface);
        password_text.setTypeface(typeface);
        SignIn.setTypeface(typeface);
        register.setTypeface(typeface);
        TextView welcomeText = (TextView) findViewById(R.id.welcomeToFestember);
        welcomeText.setTypeface(typeface);
        i= new Intent(LoginActivity.this,Hscreen.class);
        checkBox.setTypeface(typeface);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password_text.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                if (!isChecked) {
                    password_text.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
    private static Boolean isValidEmail(String email)
    {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
    public void callRegistration(View view){
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }
    public void callSignIn(View view){
        user_mail = username_text.getText().toString();
        password = password_text.getText().toString();
        if(user_mail.length()==0)username_text.setError("Invalid username");
            // else if(!isValidEmail(username))username_text.setError("Invalid Username");
        else if(password.length()==0)password_text.setError("Invalid password");
        else  check_login();



    }
    public void check_login(){
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Utilities.url_auth,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            String error = jsonResponse.getString("data");
                            pDialog.dismiss();
                            switch (status) {
                                case 0:
                                    Toast.makeText(LoginActivity.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);

                                    break;
                                case 1:case 2:

                                    SharedPreferences.Editor editor = prefs.edit();
                                    Utilities.status=1;
                                    editor.putInt("Logged_in", Utilities.status);
                                    editor.putString("f_email", user_mail);
                                    Utilities.f_email = user_mail;
                                    editor.putString("f_pass", password);
                                    Utilities.f_pass = password;
                                    editor.apply();

                                    getDetails();
                                    //Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                                    //startActivity(i);
                                    //finish();
                                    break;

                                case 3:
                                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);
                                    break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Please check your internet and try again", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("user_email", user_mail);
                params.put("user_pass", password);
                return params;
            }
        };
        int socketTimeout = 10000;//10 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(postRequest);
        //Volley.newRequestQueue(this).add(postRequest);

    }


    public void gcmreg()
    {

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }
                    regid = gcm.register("835229264934");
                    msg = regid;
                    pregister(LoginActivity.this, regid);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    Log.e("gcm_status", msg);
                }
                return msg;
            }
        }.execute(null, null, null);
    }


    public void getDetails(){

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Utilities.url_details,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int status = jsonResponse.getInt("status");
                            String data = jsonResponse.getString("data");
                            JSONObject jsonObject= new JSONObject(data);

                            String id=jsonObject.getString("user_id");
                            String name=jsonObject.getString("user_name");
                            String email=jsonObject.getString("user_email");
                            String fullname=jsonObject.getString("user_fullname");

                            pDialog.dismiss();

                            switch (status) {
                                case 0:
                                    Toast.makeText(LoginActivity.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);

                                    break;
                                case 1:case 2:

                                    SharedPreferences.Editor editor = prefs.edit();
                                    //editor.putInt("Logged_in", Utilities.status);
                                    editor.putString("f_email", email);
                                    editor.putString("f_id", id);
                                    editor.putString("f_name", name);
                                    editor.putString("f_fullname",fullname);
                                    editor.putString("f_pass", password);
                                    Utilities.f_email = email;
                                    Utilities.f_id=id;
                                    Profile.setProfile(Integer.valueOf(id),name,email,fullname);
                                    editor.apply();
                                    callDescParse(Utilities.url_eventsdesc,"description.txt");
                                    callDescParse(Utilities.url_events,"upcoming.txt");
                                    gcmreg();
                                    startActivity(i);
                                    finish();
                                    break;

                                 case 3:
                                    Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                                    username_text.setText("");
                                    password_text.setText("");
                                    SignIn.setClickable(true);
                                    break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("user_email", Utilities.f_email);
                params.put("user_pass", Utilities.f_pass);
                return params;
            }
        };
        int socketTimeout = 10000;//10 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        postRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(postRequest);
        //Volley.newRequestQueue(this).add(postRequest);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    public void callDescParse(String url, final String filename){


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // Result handling
                        System.out.println("resopnse saving");
                        fileOps.writeToFile(response, filename);
                        Utilities.check_desc=1;
                        Utilities.upcoming_check=1;
                        SharedPreferences.Editor editor = Utilities.desc_check.edit();
                        editor.putInt("check",1);
                        editor.putInt("upcoming_parse",1);
                        editor.apply();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                Log.e("file", "Something went wrong!");
                error.printStackTrace();

            }
        });
        // Add the request to the queue
        int socketTimeout = 10000;//10 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(stringRequest);

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


    void pregister(final Context context, final String regId) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                Log.e("gcm_status", "registering device (regId = " + regId + ")");
                String serverUrl = Utilities.url_gcm;
                Map<String, String> paramss = new HashMap<String, String>();
                paramss.put("fes_id", Utilities.f_id);
                paramss.put("gcm_id", regId);
                for (int i = 1; i <= 1; i++) {
                    Log.e("gcm_status", "Attempt #" + i + " to register");
                    try {
                        post(serverUrl, paramss);
                        return msg;
                    } catch (IOException e) {
                        Log.e("gcm_status", "Failed to register on attempt " + i + ":" + e);
                    }
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
            }
        }.execute(null, null, null);

    }

    private void post(String endpoint, Map<String, String> params)
            throws IOException {

        URL url;
        try {
            url = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + endpoint);
        }
        StringBuilder bodyBuilder = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        // constructs the POST body using the parameters
        while (iterator.hasNext()) {
            Map.Entry<String, String> param = iterator.next();
            bodyBuilder.append(param.getKey()).append('=')
                    .append(param.getValue());
            if (iterator.hasNext()) {
                bodyBuilder.append('&');
            }
        }
        String body = bodyBuilder.toString();
        byte[] bytes = body.getBytes();
        Log.e("gcm_status", "Posting '" + body + "' to " + url);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setFixedLengthStreamingMode(bytes.length);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=UTF-8");
            // post the request
            OutputStream out = conn.getOutputStream();
            out.write(bytes);
            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    Log.e("check", line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            out.close();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

}
