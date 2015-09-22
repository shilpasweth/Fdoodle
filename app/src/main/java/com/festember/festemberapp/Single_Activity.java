package com.festember.festemberapp;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.festember.festemberapp.R;
public class Single_Activity extends FragmentActivity implements ActionBar.TabListener{
private ViewPager viewPager;
private TabPagerAdapter mAdapter;
private ActionBar actionBar;
int e_id=0;
String name="";
String desc,cont;
// Tab titles
private String[] tabs = { "Introduction", "Rules & Format", "Prizes","Timings","Contacts" };
private String[] tabs2 = { "Introduction","Contacts" };

TextView title;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_single_);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
                name = extras.getString("name");
                e_id  = extras.getInt("id");
                desc = extras.getString("desc");
                cont = extras.getString("contact");
            Utilities.event_name=name;
            Utilities.event_desc=desc;
            Utilities.event_contact=cont;



                // and get whatever type user account id is

        }



        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ColorEvents)));
        // actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.ColorPrimaryDark)));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

@Override
public void onPageSelected(int position) {
        // on changing the page
        // make respected tab selected
        actionBar.setSelectedNavigationItem(position);

        }

@Override
public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

@Override
public void onPageScrollStateChanged(int arg0) {
        }
        });

        // Adding Tabs
        for (String tab_name : tabs2) {
        actionBar.addTab(actionBar.newTab().setText(tab_name)
        .setTabListener(this));
        }
        }


@Override
public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
       // TextView t = (TextView)viewPager.findViewById(R.id.introText);
        //t.setText(name);
        }

@Override
public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

@Override
public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    public void callRegEvent(View view){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Single_Activity.this);

        // set title
        alertDialogBuilder.setTitle("Register");




        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, final int id) {
                        // if this button is clicked, close
                        // current activity


                        String url = "https://api.festember.com/user/register/event";
                        final ProgressDialog pDialog = new ProgressDialog(Single_Activity.this);
                        pDialog.setMessage("Loading...");
                        pDialog.setCancelable(false);
                        pDialog.setCanceledOnTouchOutside(false);
                        pDialog.show();

                        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.e("TAG",Utilities.f_id);
                                        Log.e("TAG",Utilities.f_pass);
                                        Log.e("TAG",""+e_id);



                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            int status = jsonResponse.getInt("status");
                                            String error = jsonResponse.getString("data");
                                            pDialog.dismiss();
                                            Toast.makeText(Single_Activity.this, error, Toast.LENGTH_SHORT).show();
                                            System.out.println(error);



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            System.out.println("error");
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        pDialog.dismiss();
                                        error.printStackTrace();
                                        Toast.makeText(Single_Activity.this, "Please check your internet and try again", Toast.LENGTH_LONG).show();

                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams()
                            {
                                Map<String, String> params = new HashMap<>();
                                // the POST parameters:
                                params.put("user_id", Utilities.f_id);
                                params.put("user_pass", Utilities.f_pass);
                                params.put("event_id",""+e_id);

                                return params;
                            }
                        };
                        Volley.newRequestQueue(Single_Activity.this).add(postRequest);

                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();




    }
    }



