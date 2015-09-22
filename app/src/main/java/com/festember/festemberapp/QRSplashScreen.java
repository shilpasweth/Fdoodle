package com.festember.festemberapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.festember.festemberapp.R;

public class QRSplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrsplash_screen);
        Utilities.qr_prefs = getSharedPreferences("qr_code_prefs", 0);
        Utilities.qr_status= Utilities.qr_prefs.getInt("status", 0);
        if(Utilities.qr_status!=0)
        {
            Utilities.webmail_username= Utilities.qr_prefs.getString("user_name","User");
            Utilities.webmail_password= Utilities.qr_prefs.getString("user_pass","Password");
        }
        Intent i;
        switch(Utilities.qr_status)
        {
            case 0:i=new Intent(this,QRCodeLogin.class);
                startActivity(i);
                break;

            case 1:
                Toast.makeText(this,"You have not registered. :(", Toast.LENGTH_SHORT).show();
                break;

            default:
                i = new Intent(this,QRCodeWelcomePage.class);
                startActivity(i);
                break;
        }
        finish();
    }
}
