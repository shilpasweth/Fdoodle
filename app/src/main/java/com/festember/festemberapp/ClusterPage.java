package com.festember.festemberapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.festember.festemberapp.R;

public class ClusterPage extends AppCompatActivity {

    private List<Cluster> clusters;
    public List<Event> events;
    public List<Event> cluster0;
    public List<Event> cluster1;
    public List<Event> cluster2;
    public List<Event> cluster3;
    public List<Event> cluster4;
    public List<Event> cluster5;
    public List<Event> cluster6;
    public List<Event> cluster7;
    public List<Event> cluster8;
    public List<Event> cluster9;
    public List<Event> cluster10;
    RecyclerView rv;
    int touch_check=1;
    int cluster_no=-1;
    private  Read_write_file fileOps;
    EventAdapter adapter;
    GridLayoutManager llm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cluster_page);
        fileOps = new Read_write_file(this);
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        rv = (RecyclerView)findViewById(R.id.rv);
        initializeCluster();
        initializeEvents();
        separateEvents();
        Utilities.initcolours(ClusterPage.this);
         llm = new GridLayoutManager(ClusterPage.this,2);

        RecycleAdapter_cluster adapter = new RecycleAdapter_cluster(clusters);
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    //Drawer.closeDrawers();
                    int pos = recyclerView.getChildPosition(child);
                    View single = recyclerView.getChildAt(recyclerView.getChildPosition(child));
                 //   TextView t = (TextView)single.findViewById(R.id.cluster_name);
                   ///String s = clusters.get(pos).getName();
                    //Toast.makeText(ClusterPage.this, s, Toast.LENGTH_SHORT).show();
                    if(touch_check==1) {
                        touch_check = 2;
                        switch (pos) {
                            case 0:
                                EventAdapter adapter = new EventAdapter(cluster0);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=0;
                                break;
                            case 1:
                                adapter = new EventAdapter(cluster1);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=1;
                                break;
                            case 2:
                                adapter = new EventAdapter(cluster2);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=2;
                                break;
                            case 3:
                                adapter = new EventAdapter(cluster3);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=3;
                                break;
                            case 4:
                                adapter = new EventAdapter(cluster4);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=4;
                                break;
                            case 5:
                                adapter = new EventAdapter(cluster5);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=5;
                                break;
                            case 6:
                                adapter = new EventAdapter(cluster6);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=6;
                                break;
                            case 7:
                                adapter = new EventAdapter(cluster7);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=7;
                                break;
                            case 8:
                                adapter = new EventAdapter(cluster8);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=8;
                                break;
                            case 9:
                                adapter = new EventAdapter(cluster9);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=9;
                                break;
                            case 10:
                                adapter = new EventAdapter(cluster10);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(llm);
                                cluster_no=10;
                                break;


                        }
                    }
                    else {

                        Intent i = new Intent(ClusterPage.this,Single_Activity.class);
                        switch(cluster_no){

                            case 0: i.putExtra("id",cluster0.get(pos).id);
                                    i.putExtra("desc",stringPareser1(cluster0.get(pos).desc));
                                    i.putExtra("name",stringPareser1(cluster0.get(pos).name));
                                    i.putExtra("contact",stringPareser1(cluster0.get(pos).contact));

                                    break;
                            case 1: i.putExtra("id",cluster1.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster1.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster1.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster1.get(pos).contact));
                                break;
                            case 2: i.putExtra("id",cluster2.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster2.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster2.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster2.get(pos).contact));
                                break;
                            case 3: i.putExtra("id",cluster3.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster3.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster3.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster3.get(pos).contact));
                                break;
                            case 4: i.putExtra("id",cluster4.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster4.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster4.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster4.get(pos).contact));
                                break;
                            case 5: i.putExtra("id",cluster5.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster5.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster5.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster5.get(pos).contact));
                                break;
                            case 6: i.putExtra("id",cluster6.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster6.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster6.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster6.get(pos).contact));
                                break;
                            case 7: i.putExtra("id",cluster7.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster7.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster7.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster7.get(pos).contact));
                                break;
                            case 8: i.putExtra("id",cluster8.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster8.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster8.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster8.get(pos).contact));
                                break;
                            case 9: i.putExtra("id",cluster9.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster9.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster9.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster9.get(pos).contact));

                                Log.e("TAG", Utilities.f_id);
                                Log.e("TAG", Utilities.f_pass);
                                Log.e("TAG", "" + cluster9.get(pos).id);
                                break;
                            case 10: i.putExtra("id",cluster10.get(pos).id);
                                i.putExtra("desc",stringPareser1(cluster10.get(pos).desc));
                                i.putExtra("name",stringPareser1(cluster10.get(pos).name));
                                i.putExtra("contact",stringPareser1(cluster10.get(pos).contact));
                                break;

                        }
                        startActivity(i);


                    }


                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }
        });
        rv.setLayoutManager(llm);






    }
    public void separateEvents(){

        cluster0 = new ArrayList<>();
        cluster1 = new ArrayList<>();
        cluster2 = new ArrayList<>();
        cluster3 = new ArrayList<>();
        cluster4 = new ArrayList<>();
        cluster5 = new ArrayList<>();
        cluster6 = new ArrayList<>();
        cluster7 = new ArrayList<>();
        cluster8 = new ArrayList<>();
        cluster9 = new ArrayList<>();
        cluster10 = new ArrayList<>();
        for(int i=0;i<events.size();i++){

            Event temp = new Event(events.get(i).id,
                    events.get(i).name,
                    events.get(i).start_time,
                    events.get(i).end_time,
                    events.get(i).venue,
                    events.get(i).desc,
                    events.get(i).date,
                    events.get(i).cluster,
                    events.get(i).contact
            );
            if(temp.cluster.equals("dance"))cluster0.add(events.get(i));
            else if(temp.cluster.equals("music"))cluster1.add(temp);
            else if(temp.cluster.equals("shruthilaya"))cluster2.add(events.get(i));
            else if(temp.cluster.equals("dramatics"))cluster3.add(events.get(i));
            else if(temp.cluster.equals("cinematics"))cluster4.add(events.get(i));
            else if(temp.cluster.equals("fashionitas"))cluster5.add(events.get(i));
            else if(temp.cluster.equals("gaming"))cluster6.add(events.get(i));
            else if(temp.cluster.equals("arts"))cluster7.add(events.get(i));
            else if(temp.cluster.equals("hindilits"))cluster8.add(events.get(i));
            else if(temp.cluster.equals("englishlits"))cluster9.add(events.get(i));
            else if(temp.cluster.equals("tamillits"))cluster10.add(events.get(i));

            }
        }






    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
    public void callEvents(RecyclerView v){
        View r = v.getChildAt(0);


    }

    public  class Cluster {

        String name;

        Cluster(String name) {
            this.name = name;


        }

        public String getName() {
            return name;
        }
    }
        public void initializeCluster(){
            clusters = new ArrayList<>();
            clusters.add(new Cluster("Dance"));
            clusters.add(new Cluster("Music"));
            clusters.add(new Cluster("Shruthilaya"));
            clusters.add(new Cluster("Dramatics"));
            clusters.add(new Cluster("Cinematics"));
            clusters.add(new Cluster("Fashionitas"));
            clusters.add(new Cluster("Gaming"));
            clusters.add(new Cluster("Arts"));
            clusters.add(new Cluster("Hindi Lits"));
            clusters.add(new Cluster("English Lits"));
            clusters.add(new Cluster("Tamil Lits"));

        }


    public void initializeEvents(){

        events = new ArrayList<>();
        String[] description = new String[100];





            String response = fileOps.readFromFile("description.txt");
            try {
                JSONObject jsonObject = new JSONObject(response);
                String data = jsonObject.getString("data");
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    description[i] = jsonObject.getString("event_desc");

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



           response = fileOps.readFromFile("upcoming.txt");
            try {
                JSONObject jsonObject = new JSONObject(response);
                String data = jsonObject.getString("data");

                JSONArray jsonArray = new JSONArray(data);
                JSONArray jsonArray2 = new JSONArray(Utilities.contacts);
                JSONObject jsonObject2;

                int i2=0;
                String co="contact";
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    jsonObject2= jsonArray2.getJSONObject(i2);
                    if(jsonObject.getString("event_name").equals(jsonObject2.getString("event_name")))
                    {
                        co=jsonObject2.getString("contact");
                        i2++;
                    }
                    else
                    {
                        co="contact";
                    }
                    events.add(new Event(
                            jsonObject.getInt("event_id"),
                            jsonObject.getString("event_name"),
                            jsonObject.getString("event_start_time"),
                            jsonObject.getString("event_end_time"),
                            jsonObject.getString("event_venue"),
                            description[i],
                            jsonObject.getString("event_date"),
                            jsonObject.getString("event_cluster"),
                            co


                    ));


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


    }
    public class Event {

        public int id ;
        public String name;
        public String start_time;
        public String end_time;
        public String venue;
        public String desc;
        public String date;
        public String cluster;
        public String contact;
        private Read_write_file fileOps;



        public Event(int id,String name,String start_time,String end_time,String venue,String desc,String date,String cluster,String contact)
        {

            this.id=id;
            this.name=name;
            this.start_time=start_time;
            this.end_time=end_time;
            this.venue=venue;
            this.cluster=cluster;
            this.date=date;
            this.contact=contact;
            this.desc=desc;




        }




    }



    @Override
    public void onBackPressed() {

        if(touch_check==2) {
            touch_check = 1;
            RecycleAdapter_cluster adapter = new RecycleAdapter_cluster(clusters);
            rv.setAdapter(adapter);
            rv.setLayoutManager(llm);
        }
        else if(touch_check==1){
            super.onBackPressed();
        }

    }
    public String stringPareser1(String t){
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

