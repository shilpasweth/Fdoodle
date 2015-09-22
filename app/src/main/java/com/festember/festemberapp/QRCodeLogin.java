package com.festember.festemberapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.festember.festemberapp.R;
public class QRCodeLogin extends Activity {
    String rollNumber;
    String password;
    EditText rollNumberText, passwordText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qrcode_login);
        handleButtonClick();
    }

    private void handleButtonClick() {
        rollNumberText = (EditText) findViewById(R.id.rollNumber);
        passwordText = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.signInButton);
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/gnu.ttf");
        button.setTypeface(face);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollNumber = rollNumberText.getText().toString();
                if (rollNumber.length() != 9)
                    rollNumberText.setError("Invalid roll number");
                else {
                    password = passwordText.getText().toString();
                    //Pass rollNumber and password to the server
                    new myAsyncTask().execute();


                    button.setClickable(false);
                }
            }
        });
        CheckBox checkBox = (CheckBox) findViewById(R.id.showPasswordCheckBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                if (!isChecked) {
                    passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    class myAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog myPd_ring = null;
        @Override
        protected void onPreExecute() {

            myPd_ring  = new ProgressDialog (QRCodeLogin.this);
            myPd_ring.setMessage("Loading...");
            myPd_ring.setCancelable(false);
            myPd_ring.setCanceledOnTouchOutside(false);
            myPd_ring.show();

        }

        @Override
        protected String doInBackground(String... strings) {
            String error = null;
            HttpClient httpclient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            HttpPost httppost = new HttpPost(Utilities.url_qr_auth);
            JSONObject jsonObject;

            try {
                List nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("user_name", rollNumber));
                nameValuePairs.add(new BasicNameValuePair("user_pass", password));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                HttpResponse response = httpclient.execute(httppost);
                httpEntity = response.getEntity();
                String s = EntityUtils.toString(httpEntity);
                try {
                    jsonObject = new JSONObject(s);
                    Utilities.qr_status = jsonObject.getInt("auth");
                    error = jsonObject.getString("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (ClientProtocolException e) {
            } catch (IOException e) {
            }
            return error;
        }

        @Override
        protected void onPostExecute(String error) {
            super.onPostExecute(error);
            System.out.println("Error: " + error);
            myPd_ring.dismiss();
            switch (Utilities.qr_status) {
                case 0:
                    //Error connecting
                    Toast.makeText(QRCodeLogin.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();
                    rollNumberText.setText("");
                    passwordText.setText("");
                    button.setClickable(true);
                    break;
                case 1:
                    //Registration not yet done
                    Toast.makeText(QRCodeLogin.this,"You have not registered.", Toast.LENGTH_SHORT).show();
/*
                    Intent intent = new Intent(getBaseContext(), Coupon.class);
                    SharedPreferences.Editor editor = Utilities.prefs.edit();
                    editor.putInt("status", Utilities.status);
                    editor.putString("user_name", rollNumber);
                    Utilities.username = rollNumber;
                    editor.putString("user_pass", password);
                    Utilities.password = password;
                    editor.apply();
                    startActivity(intent);
                    finish();
*/
                    break;
                case 2:
                    //Registration complete
                    Intent intent = new Intent(QRCodeLogin.this, QRCodeWelcomePage.class);
                    Utilities.webmail_username = rollNumber;
                    Utilities.webmail_password = password;

                    Utilities.qr_prefs = getSharedPreferences("qr_code_prefs",0);
                    SharedPreferences.Editor editor2 = Utilities.qr_prefs.edit();
                    editor2.putInt("status", Utilities.qr_status);
                    editor2.putString("user_name", rollNumber);
                    editor2.putString("user_pass", password);
                    editor2.apply();

                    startActivity(intent);
                    finish();
                    break;
                case 3:
                    Toast.makeText(QRCodeLogin.this, "Your account is not on the system. Please contact Festember OC", Toast.LENGTH_SHORT).show();
                    rollNumberText.setText("");
                    passwordText.setText("");
                    button.setClickable(true);
                    break;
            }
        }
    }
}
