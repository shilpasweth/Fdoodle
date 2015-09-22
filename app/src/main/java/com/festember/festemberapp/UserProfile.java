package com.festember.festemberapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.festember.festemberapp.R;

public class UserProfile extends ActionBarActivity {

    SharedPreferences prefs=null;
    ListView regEventsList;
    ArrayAdapter<String> eventsArrayAdapter;
    String o="#BreakTheMonotony";
    TextView lt;
    Read_write_file file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_user_profile);

        file=new Read_write_file(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.ColorProfile)));
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Profile </font>"));
        setData();
        buttonWork();
    }

    private void buttonWork()
    {
        final Button voucherOne = (Button) findViewById(R.id.voucherOne);
        final Button voucherTwo = (Button) findViewById(R.id.voucherTwo);

        voucherOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfile.this, "Voucher saved to gallery", Toast.LENGTH_SHORT).show();
                voucherOne.setClickable(false);
            }
        });

        voucherTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserProfile.this, "Voucher saved to gallery", Toast.LENGTH_SHORT).show();
                voucherTwo.setClickable(false);
            }
        });
    }


    private void setData() {
        Typeface face = Typeface.createFromAsset(getApplicationContext().getAssets(),
                "fonts/gnu.ttf");
        String name = null, F_ID = null, email_ID = null, couponCode = null;
        ArrayList<String> reg_events = new ArrayList<>();
        TextView nameView, fIDView, emailIDView, couponView;
        nameView = (TextView) findViewById(R.id.welcomeText);
        nameView.setTypeface(face);
        fIDView = (TextView) findViewById(R.id.f_id);
        fIDView.setTypeface(face);
        emailIDView = (TextView) findViewById(R.id.email_id);
        emailIDView.setTypeface(face);


        //Getting the details from SharedPreferences/Utilities
        //Get name, F_ID, college, email_ID, reg_events, couponCode
            for(int i=0;i<15;i++)
            {
                reg_events.add("Event "+i);
            }
        //Setting the details

        regEventsList = (ListView) findViewById(R.id.registeredEventsListView);
        //regEventsList.setVisibility(View.INVISIBLE);
        lt=(TextView)findViewById(R.id.loadingtext);
        lt.setTypeface(face);

        nameView.setText(nameView.getText().toString() + Profile.name + "!");
        fIDView.setText(fIDView.getText().toString() + String.valueOf(Profile.id));
        emailIDView.setText(emailIDView.getText().toString() + Profile.email);
        //couponView.setText(couponView.getText().toString() + Profile.coupon);


        //if(eventsArrayAdapter!=null)
        //regEventsList.setAdapter(eventsArrayAdapter);
        check_events();
    }



    public void check_events(){

        /*final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);



        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
*/

        StringRequest postRequest = new StringRequest(Request.Method.POST, Utilities.url_profile_events,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            file.writeToFile(response,"eventreglist.txt");
                            JSONObject jsonResponse = new JSONObject(response);
                            //Log.e("params",jsonResponse.toString());
                            int status = jsonResponse.getInt("status");
                            //String error = jsonResponse.getString("data");
                            //pDialog.dismiss();
                            switch (status) {
                                case 0:
                                    Toast.makeText(UserProfile.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();


                                    break;
                                case 1:case 2:

                                    JSONArray elist=jsonResponse.getJSONArray("data");
                                    Log.e("paramllist",elist.toString());
                                    int i=0;
                                    Profile.eventlist.clear();
                                    while(i<elist.length())
                                    {
                                        JSONObject j=(JSONObject)elist.get(i);

                                        Log.e("param",j.toString());
                                        i++;
                                        Profile.eventlist.add(stringParser(j.get("event_name").toString()));
                                    }
                                    //eventsArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.single_list_item,Profile.eventlist);



                                    final Typeface mFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gnu.ttf");
                                    eventsArrayAdapter = new ArrayAdapter<String>(UserProfile.this, R.layout.single_list_item, R.id.regevent, Profile.eventlist) {
                                        @Override
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            View view = super.getView(position, convertView, parent);
                                            TextView textview = (TextView) view;
                                            textview.setTypeface(mFont);
                                            return textview;

                                        }



                                    };

                                    lt.setVisibility(View.INVISIBLE);
                                    regEventsList.setAdapter(eventsArrayAdapter);
                                    //Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                                    //startActivity(i);
                                    //finish();
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
                        //pDialog.dismiss();
                        error.printStackTrace();
                        lt.setText("Could not update :/");
                        String response=file.readFromFile("eventreglist.txt");
                        try {
                            file.writeToFile(response,"eventreglist.txt");
                            JSONObject jsonResponse = new JSONObject(response);
                            //Log.e("params",jsonResponse.toString());
                            int status = jsonResponse.getInt("status");
                            //String error = jsonResponse.getString("data");
                            //pDialog.dismiss();
                            switch (status) {
                                case 0:
                                    Toast.makeText(UserProfile.this, "There was a problem connecting to the server. Please check your username and password and try again.", Toast.LENGTH_LONG).show();


                                    break;
                                case 1:case 2:

                                    JSONArray elist=jsonResponse.getJSONArray("data");
                                    Log.e("paramllist",elist.toString());
                                    int i=0;
                                    Profile.eventlist.clear();
                                    while(i<elist.length())
                                    {
                                        JSONObject j=(JSONObject)elist.get(i);

                                        Log.e("param",j.toString());
                                        i++;
                                        Profile.eventlist.add(stringParser(j.get("event_name").toString()));
                                    }
                                    //eventsArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.single_list_item,Profile.eventlist);



                                    final Typeface mFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gnu.ttf");
                                    eventsArrayAdapter = new ArrayAdapter<String>(UserProfile.this, R.layout.single_list_item, R.id.regevent, Profile.eventlist) {
                                        @Override
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            View view = super.getView(position, convertView, parent);
                                            TextView textview = (TextView) view;
                                            textview.setTypeface(mFont);
                                            return textview;

                                        }



                                    };

                                    lt.setVisibility(View.INVISIBLE);
                                    regEventsList.setAdapter(eventsArrayAdapter);
                                    //Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                                    //startActivity(i);
                                    //finish();
                                    break;


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(UserProfile.this, "Error updating. Please try later.", Toast.LENGTH_LONG).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<>();
                // the POST parameters:
                params.put("user_id",Utilities.f_id);
                params.put("user_pass", Utilities.f_pass);
                Log.e("params:",Utilities.f_email+"-"+Utilities.f_pass);
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
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            //Logout
            Toast.makeText(UserProfile.this, "Logout", Toast.LENGTH_SHORT).show();
            Utilities.status=0;
            prefs=getSharedPreferences("LogInPrefs",0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("Logged_in", Utilities.status);
            editor.putString("f_id","");
            editor.putString("f_email","");
            editor.putString("f_pass","");

            editor.apply();
            Intent i=new Intent(UserProfile.this,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            //finish();


            return true;
        }
        if (id==R.id.qr_code)
        {
            //Open QR Code activity
            Intent intent = new Intent(UserProfile.this, QRSplashScreen.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public String stringParser(String t){
        String temp2="";
        if (t.contains("_")) {
            // Split it.
            //t.substring(0,t.indexOf("_")-1);



            String[] temp = t.split("_");
            for(int j=0;j<temp.length;j++) {
                String temp3 = temp[j].substring(0,1).toUpperCase() + temp[j].substring(1);
                temp2 = temp2 + temp3 + " ";
            }


        }
        else temp2=t.substring(0,1).toUpperCase() + t.substring(1);

        return temp2;
    }
}
