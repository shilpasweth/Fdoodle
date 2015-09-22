package com.festember.festemberapp;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.festember.festemberapp.R;
public class QRCodeWelcomePage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_welcome_page);
        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText(welcomeText.getText().toString() + Utilities.webmail_username);
        if (Utilities.qr_status == 2) {
            ImageView qrCodeImage = (ImageView) findViewById(R.id.qr_code_image);
            qrCodeImage.setVisibility(View.VISIBLE);
            Bitmap bitmap = new SaveImage(Utilities.webmail_username, null).loadFromCacheFile();
            if (bitmap == null) new myAsyncTask().execute();
            else {
                qrCodeImage.setImageBitmap(bitmap);
            }
        }
    }

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_qrcode_welcome_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            //Logout
/*
            SharedPreferences.Editor editor = Utilities.prefs.edit();
            editor.putInt("status", 0);
            editor.putString("user_name", null);
            editor.putString("user_pass", null);
            editor.apply();
*/
            Utilities.webmail_username = Utilities.webmail_password = null;
            Intent intent = new Intent(QRCodeWelcomePage.this, QRCodeLogin.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (Utilities.qr_status != 2)
            super.onBackPressed();
        else finish();
    }

    class myAsyncTask extends AsyncTask<String, Void, Bitmap> {
        ProgressDialog myPd_ring = null;

        @Override
        protected void onPreExecute() {

            myPd_ring = new ProgressDialog(QRCodeWelcomePage.this);
            myPd_ring.setMessage("Loading...");
            myPd_ring.show();

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            HttpClient httpclient = new DefaultHttpClient();
            Bitmap image = null;
            HttpPost httppost = new HttpPost(Utilities.url_qr);
            try {
                List nameValuePairs = new ArrayList();
                nameValuePairs.add(new BasicNameValuePair("user_name", Utilities.webmail_username));
                nameValuePairs.add(new BasicNameValuePair("user_pass", Utilities.webmail_password));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                URL url2 = new URL(Utilities.url_qr);

                HttpResponse response = httpclient.execute(httppost);
                HttpEntity httpEntity = null;

                httpEntity = response.getEntity();
                byte[] img = EntityUtils.toByteArray(httpEntity);
                image = BitmapFactory.decodeByteArray(img, 0, img.length);


            } catch (ClientProtocolException e) {
            } catch (IOException e) {
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bmp) {
            super.onPostExecute(bmp);
            myPd_ring.dismiss();
            ImageView show_image = (ImageView) findViewById(R.id.qr_code_image);
            show_image.setImageBitmap(bmp);
            SaveImage save = new SaveImage(Utilities.webmail_username, bmp);
            save.saveToCacheFile(bmp);
            addImageToGallery(save.getCacheFilename(), QRCodeWelcomePage.this);

            Toast.makeText(QRCodeWelcomePage.this, "Image saved to gallery", Toast.LENGTH_LONG).show();
        }
    }

}
