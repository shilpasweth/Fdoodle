package com.festember.festemberapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.festember.festemberapp.R;

public class Hscreen extends ActionBarActivity {

    int screen_width;
    int screen_height;
    Handler myHandler;
    Runnable r;
    int w,h;
    DisplayMetrics displaymetrics;
    ImageView f[];
    int o[] = {0, 3, 2, 5, 4, 1};

    int frame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hscreen);
        this.getSupportActionBar().hide();
        displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screen_height = displaymetrics.heightPixels;
        screen_width = displaymetrics.widthPixels;
        myHandler = new Handler();
        Utilities.typeface= Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/gnu.ttf");
        frame=-00;
        Bitmap p=Bitmaphandle.decodeSampledBitmapFromResource(getResources(),this.getResources().getIdentifier("f"+String.valueOf(o[0]+1), "drawable",this.getPackageName()),screen_width,screen_height);
        w=p.getWidth();
        h=p.getHeight();
        f = new ImageView[6];
        f[0] = (ImageView) findViewById(R.id.img_cut_1);
        f[0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {

                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                    int nTouchX = (int) me.getX();
                    int nTouchY = (int) me.getY();
                    Bitmap bm = ((BitmapDrawable) f[0].getDrawable()).getBitmap();
                    //Bitmap bim=Bmpmute.convertToMutable(bm);
                    if (bm.getPixel(nTouchX*w/screen_width, nTouchY*h/screen_height) != 0) {
                        // non-transparent pixel touched,
                        Intent i = new Intent(Hscreen.this,ClusterPage.class);
                        startActivity(i);
                        return true;
                    }
                    // transparent pixel touched
                    return false;
                }


                return false;
            }
        });
        f[1] = (ImageView) findViewById(R.id.img_cut_2);
        f[1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {

                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                    int nTouchX = (int) me.getX();
                    int nTouchY = (int) me.getY();
                    Bitmap bm = ((BitmapDrawable) f[1].getDrawable()).getBitmap();
                    //Bitmap bim=Bmpmute.convertToMutable(bm);
                    if (bm.getPixel(nTouchX * w / screen_width, nTouchY * h / screen_height) != 0) {
                        // non-transparent pixel touched,
                        Intent i=new Intent(Hscreen.this,ScheduleActivity.class);
                        startActivity(i);


                        //Toast.makeText(getApplicationContext(), "Doodle", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    // transparent pixel touched
                    return false;
                }


                return false;
            }
        });
        f[2] = (ImageView) findViewById(R.id.img_cut_3);
        f[2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {

                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                    int nTouchX = (int) me.getX();
                    int nTouchY = (int) me.getY();
                    Bitmap bm = ((BitmapDrawable) f[2].getDrawable()).getBitmap();
                    //Bitmap bim=Bmpmute.convertToMutable(bm);
                    if (bm.getPixel(nTouchX * w / screen_width, nTouchY * h / screen_height) != 0) {
                        // non-transparent pixel touched,
                        Intent i=new Intent(Hscreen.this,UserProfile.class);
                        startActivity(i);
                                                // Toast.makeText(getApplicationContext(), "My Profile", Toast.LENGTH_SHORT).show();
                        return true;

                        //Toast.makeText(getApplicationContext(), "Raffle", Toast.LENGTH_SHORT).show();

                    }
                    // transparent pixel touched
                    return false;
                }


                return false;
            }
        });
        f[3] = (ImageView) findViewById(R.id.img_cut_4);
        f[3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {

                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                    int nTouchX = (int) me.getX();
                    int nTouchY = (int) me.getY();
                    Bitmap bm = ((BitmapDrawable) f[3].getDrawable()).getBitmap();
                    //Bitmap bim=Bmpmute.convertToMutable(bm);
                    if (bm.getPixel(nTouchX * w/ screen_width, nTouchY * h/ screen_height) != 0) {
                        // non-transparent pixel touched,
                        Intent i=new Intent(Hscreen.this,Painter.class);
                        startActivity(i);

                        // Toast.makeText(getApplicationContext(), "Upcoming Events", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    // transparent pixel touched
                    return false;
                }


                return false;
            }
        });
        f[4] = (ImageView) findViewById(R.id.img_cut_5);
        f[4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {

                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                    int nTouchX = (int) me.getX();
                    int nTouchY = (int) me.getY();
                    Bitmap bm = ((BitmapDrawable) f[4].getDrawable()).getBitmap();
                    //Bitmap bim=Bmpmute.convertToMutable(bm);
                    if (bm.getPixel(nTouchX * w / screen_width, nTouchY * h / screen_height) != 0) {
                        // non-transparent pixel touched,
                        Intent i=new Intent(Hscreen.this,RaffleActivity.class);
                        i.putExtra("callMode",1);
                        startActivity(i);
                    }
                    // transparent pixel touched
                    return false;
                }


                return false;
            }
        });
        f[5] = (ImageView) findViewById(R.id.img_cut_6);
        f[5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me) {

                if (me.getAction() == MotionEvent.ACTION_DOWN) {
                    //Toast.makeText(getApplicationContext(), "touch", Toast.LENGTH_SHORT).show();
                    int nTouchX = (int) me.getX();
                    int nTouchY = (int) me.getY();
                    Bitmap bm = ((BitmapDrawable) f[5].getDrawable()).getBitmap();
                    //Bitmap bim=Bmpmute.convertToMutable(bm);
                    if (bm.getPixel(nTouchX * w / screen_width, nTouchY * h / screen_height) != 0) {
                        // non-transparent pixel touched,
                        Intent i=new Intent(Hscreen.this,UpcomingActivity.class);
                        startActivity(i);

                    }
                    // transparent pixel touched
                    return false;
                }


                return false;
            }
        });
/*
        Bitmaphandle.loadBitmap(R.drawable.f1,f[0]);
        Bitmaphandle.loadBitmap(R.drawable.f2,f[1]);
        Bitmaphandle.loadBitmap(R.drawable.f3,f[2]);
        Bitmaphandle.loadBitmap(R.drawable.f4,f[3]);
        Bitmaphandle.loadBitmap(R.drawable.f5,f[4]);
        Bitmaphandle.loadBitmap(R.drawable.f6,f[5]);
*/
        StartAnimation(0);


        final Animation anim[]=new Animation[6];
        for(int k=0;k<6;k++)
        {
            anim[k]=new ScaleAnimation(0.6f,1f,0.6f,1f,Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            anim[k].setDuration(300);
            anim[k].setInterpolator(new LinearInterpolator());
            final int finalK = k;
            anim[k].setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    f[o[frame]].setImageBitmap(Bitmaphandle.decodeSampledBitmapFromResource(getResources(),getApplicationContext().getResources().getIdentifier("f"+String.valueOf(o[frame]+1), "drawable",getApplicationContext().getPackageName()),screen_width,screen_height));
                    f[o[frame]].setVisibility(View.VISIBLE);
                    if(finalK<5) {
                        ++frame;
                        f[o[frame]].startAnimation(anim[finalK +1]);
                    }


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            //StartcomplexAnimation(anim[0]);


        }






    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.e("cycle","postcreate");
    }

    @Override
    protected void onPostResume() {
        Log.e("cycle","postresumed");
        super.onPostResume();
    }

    @Override
    protected void onStop() {
        Log.e("cycle","stopped");
        super.onStop();
    }


    public void StartcomplexAnimation(Animation anim)
    {
        f[o[0]].setImageBitmap(Bitmaphandle.decodeSampledBitmapFromResource(getResources(),this.getResources().getIdentifier("f"+String.valueOf(o[0]+1), "drawable",this.getPackageName()),screen_width,screen_height));
        f[o[0]].setVisibility(View.VISIBLE);
        f[o[frame]].startAnimation(anim);
    }
    public void StartAnimation(final int i) {



        //Bitmaphandle.loadBitmap(this.getResources().getIdentifier("f"+String.valueOf(i+1), "drawable",this.getPackageName()),f[i]);

        f[o[i]].setImageBitmap(Bitmaphandle.decodeSampledBitmapFromResource(getResources(),this.getResources().getIdentifier("f"+String.valueOf(o[i]+1), "drawable",this.getPackageName()),screen_width,screen_height));
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                f[o[i]].setVisibility(View.VISIBLE);
                if (i <= 4) StartAnimation(i + 1);
            }
        }, 300-40*i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hscreen, menu);
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



}