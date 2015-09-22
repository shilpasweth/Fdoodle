package com.festember.festemberapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;

import java.util.ArrayList;

import com.festember.festemberapp.R;
public class Utilities
{


    public static int screen_width;
    public static int screen_height;




    public static String url_events="https://api.festember.com/events/list";
    public static String url_eventsdesc="https://api.festember.com/events/desclist";
    public static String url_registerevent="https://";
    public static String url_gcm="https://festember.com/~kousik/festember-app-gcm/register.php";
    public static String url_profile_events="https://api.festember.com/user/getEvents";
    public static String url_raffle="https://festember.com/final15/festember15api/festember-raffle/raffleapi.php";
    public static String url_reg = "https://api.festember.com/user/register";
    public static String url_auth = "https://api.festember.com/user/auth";
    public static String url_details = "https://api.festember.com/user/getDetails";

    public static String f_id="";
    public static String f_pass="";
    public static String f_email="";

//Raffle module
    public static String fb_name;
    public static String fb_id;
    public static int status=0;
    public static int check_desc = 0;

        //Status 0 - Not logged into the app (Festember registration)
        //Status 1 - Logged into the app (Festember registration)
        //Status 2 - Logged into the app + Facebook registration
        //Status 3 - Logged into the app + Facebook registration + Facebook shared #festember
        //Status 4 - Logged into the app + Logged out of FB + 1 coupon received.
        //Status 5 - Logged into the app + Logged out of FB + #festember coupons received.
        //Status 6 - Logged into the app + Facebook registration + Facebook doodle shared
        //Status 7 - Logged into the app + Logged out of FB + Facebook doodle shared.
    public static String[] coupons = new String[11];

//Doodle module
    public static Bitmap doodleBitmap = null;

//QR Code module
    public static String webmail_username="";
    public static String webmail_password="";
    public static SharedPreferences qr_prefs;
    public static SharedPreferences desc_check;
    public static int upcoming_check;
    public static String url_qr = "https://festember.com/final15/festember15api/mobile_tshirt_qr.php";
    public static String url_qr_auth = "https://festember.com/final15/festember15api/mobile_auth.php";
    public static int qr_status=0;

//Events
    public static String event_name;
    public static String event_desc;
    public static Typeface typeface;
    public static int event_id;
    public static  String event_contact;


   public static ArrayList<Integer> colours=new ArrayList<Integer>();

    public static void initcolours(Context context)
    {
        colours=new ArrayList<Integer>();
        //colours.add(context.getResources().getColor(R.color.ColorUpcoming));
        colours.add(context.getResources().getColor(R.color.ColorRaffle));
        colours.add(context.getResources().getColor(R.color.ColorDoodle));
        colours.add(context.getResources().getColor(R.color.ColorProfile));
        colours.add(context.getResources().getColor(R.color.ColorSchedule));
        colours.add(context.getResources().getColor(R.color.ColorEvents));
    }


    public static String contacts="[{\"event_name\": \"choreo_nite_eastern\", \"contact\": \"Rajashree - 9444351872 Chandish - 9677584041 \"}, {\"event_name\": \"choreo_nite_western\", \"contact\": \"Tirkey - 9597691637 Chandish - 9677584041 \"}, {\"event_name\": \"western_free_style_solo\", \"contact\": \"Tirkey -9597691637 Vignesh - 9442300405 \"}, {\"event_name\": \"eastern_solo_free_style\", \"contact\": \"Rajashree -9444351872 Vignesh - 9442300405 \"}, {\"event_name\": \"duet_freestyle\", \"contact\": \"Tirkey -9597691637 Chandish - 9677584041 \"}, {\"event_name\": \"tarangini\", \"contact\": \"Viswanath -9626049006 Srikrishnan - 9551446203 \"}, {\"event_name\": \"gig_a_hertz\", \"contact\": \"Ramanna -9884521394 Ashwin - 9940646574 \"}, {\"event_name\": \"acoustics\", \"contact\": \"Rahul -8939463605 Srikrishnan - 9551446203 \"}, {\"event_name\": \"a-capella\", \"contact\": \"Rahul -8939463605 Srikrishnan - 9551446203 \"}, {\"event_name\": \"carnatic_vocals_solo\", \"contact\": \" Subhashree : 8015205098 Shashank   : 9962305296   \"}, {\"event_name\": \"carnatic_instrumental_percussion\", \"contact\": \" Aravind Shankar   : 9500062966 Shashank   : 9962305296 \"}, {\"event_name\": \"group_performance\", \"contact\": \" Gayatri: 9791433760 Shashank  : 9962305296 \"}, {\"event_name\": \"carnatic_instrumental_non_percus\", \"contact\": \"Aravind Shankar   : 9500062966 Shashank   : 9962305296 \"}, {\"event_name\": \"theatrix\", \"contact\": \"  Naveena Elamaran: 9894494268 Aarthy:   7708543375 \"}, {\"event_name\": \"act_a_thon\", \"contact\": \"Naveena Elamaran   :  9894494268Kaushik  : 9566137434\"}, {\"event_name\": \"fashionitas\", \"contact\": \"Priyanka Garg - +91 8807834907 Jayti Singh - +91 9940090850 Mohammed Shaheen - +919940109885 \"}, {\"event_name\": \"dota_2\", \"contact\": \"Indra Kumar - 9444317114 Arun Kumar - 9884477106 \"}, {\"event_name\": \"fifa\", \"contact\": \"Barathwaj Parthasarathy - 9003112904 \"}, {\"event_name\": \"wall_painting\", \"contact\": \"Shobitha   8056034611  Harshita   9629476056\"}, {\"event_name\": \"shadow_art\", \"contact\": \"Jahnavi : 9629974399  Ashwin Ram :9940646574\"}, {\"event_name\": \"face_painting\", \"contact\": \"Deepak   : 8939760408 Arun :9566457974\"}, {\"event_name\": \"abstract_painting\", \"contact\": \"Jyothi : 9629984372  Jagdish:8939471453   \"}, {\"event_name\": \"collage\", \"contact\": \"Anurag   : 9445682075  Harshita  : 9629476056   \"}, {\"event_name\": \"mask_making\", \"contact\": \"Pankaj : 9043874190  Jagdish: 8939471453 \"}, {\"event_name\": \"bollywood_quiz\", \"contact\": \" Sumit Shekhar   :9487761264Ayush: 9585768274\"}, {\"event_name\": \"bluffmaster\", \"contact\": \" Vibhuti Sharma :7598149345Ayush   : 9585768274 \"}, {\"event_name\": \"extempore_speech\", \"contact\": \" Alok kumar:8438347278Abel: 9445620588 \"}, {\"event_name\": \"dumb_c\", \"contact\": \" Gaurang:9043388591Kaushik : 9566137434 \"}, {\"event_name\": \"rochak_mantrana\", \"contact\": \"Sumit Shekhar  :  9487761264Abel: 9445620588\"}, {\"event_name\": \"online_creative_writing\", \"contact\": \" Prassan Dubey  :9786182541Gautham  : 9551670430 \"}, {\"event_name\": \"grammar_quiz\", \"contact\": \" Manish Jaiswal  :9043385719Gautham  : 9551670430 \"}, {\"event_name\": \"chakravyuh\", \"contact\": \"Sumit Shekhar -09487761264 Apurv Anurag - 9965181057 \"}, {\"event_name\": \"lone_wolf_quiz\", \"contact\": \"   Subramanian Balakrishnan  - 9940291121 Ajay Srikanth  - 8056080382 Deepan - 9500141933  \"}, {\"event_name\": \"splitent_quiz\", \"contact\": \"Sameer Khan-9600236668 Sudharshan Suresh   -9500068412 Sumkh Shankar Pande -9840860833 \"}, {\"event_name\": \"india_quiz\", \"contact\": \"Arul Prakash   - 9597644287 Agastya Ramesh- 9940184836 Deepan- 9500141933 \"}, {\"event_name\": \"av_quiz\", \"contact\": \"Arvind Shankar  -9500062966 Jayadev Kumar  -9840562564 Nithin Shrivatsav - 9600153461 \"}, {\"event_name\": \"buzzer_quiz\", \"contact\": \"Senthil Hariharan-9884506158 Anirudh Janardhanan -9757395067 Vignesh Satya - 9442300405 \"}, {\"event_name\": \"jam\", \"contact\": \"Prithvi Raj -9600390225 Anugrahaa - 9840701781 \"}, {\"event_name\": \"dumb_charades\", \"contact\": \"Arvind Shankar -9500062966 Sameer Khan  -9600236668 Anugrahaa- 9840701781 \"}, {\"event_name\": \"debate\", \"contact\": \"Shreejaya Barathan -9940247526 Nithin Shrivatsav   - 9600153461 \"}, {\"event_name\": \"extempore\", \"contact\": \"Prithvi Raj -9600390225 Daksh- 9444274363 \"}, {\"event_name\": \"pot_pourri\", \"contact\": \"Prithvi Raj -9600390225 Athira Nair -9940503540 Daksh- 9444274363 \"}, {\"event_name\": \"whats_the_good_word\", \"contact\": \"Vignesh Ramanathan -8754594670 Rishi Rajasekaran   -9445395125 Ashwin Ram   - 9940646574 \"}, {\"event_name\": \"crossword\", \"contact\": \"Vignesh Ramanathan -8754594670 Sharanya Eswaran   -9487427711 Daksh- 9444274363 \"}, {\"event_name\": \"scrabble\", \"contact\": \"Arul Prakash -9597644287 Swathi Chandrashekaran -9677867973 Harshita -9629476056 \"}, {\"event_name\": \"puzzle_champ\", \"contact\": \"Sharanya Eswaran   -9487427711 Koushik Swaminathan -9176091156 Arun VJ   - 9566457974 \"}, {\"event_name\": \"villupaatu\", \"contact\": \"Sudharshan +919791109259 Nived +919094342214 \"}, {\"event_name\": \"kavithidal\", \"contact\": \"Ramji +918883862339 Aarthy +917708543375  \"}, {\"event_name\": \"kuraloviyam\", \"contact\": \"Arunesh +917200644160 Haran +918220184652 Jagdish +918939471453 \"}, {\"event_name\": \"kolam\", \"contact\": \"Prashanthi +919003200731 Sangeetha +918754433618 \"}, {\"event_name\": \"kodambakkam\", \"contact\": \"Gowshika +917708992195 Nived +919094342214 \"}, {\"event_name\": \"kalakkal_kalatta\", \"contact\": \"Sarkuna+919489823833 Aanand +918300160731 Nived +919094342214 \"}, {\"event_name\": \"kaatrodu_kadhai_pesava\", \"contact\": \"Barath +919444984097 Raghavi +919444907511 Deekshi +919791560551 \"}, {\"event_name\": \"sakalakala_vallavan\", \"contact\": \"Shamyuktha +919940144827 Vijay Anand +919962987994 Sangeetha +918754433618 \"}, {\"event_name\": \"varthai_vilayattu\", \"contact\": \"Swathi +918939004828 Deekshi +919791560551 \"}, {\"event_name\": \"uyarthani_semmozhi\", \"contact\": \"Shalini +919597569085 Aravind +919841056035 \"}, {\"event_name\": \"koothambalam\", \"contact\": \"Monish Raj M D +919566094829 Aravind +919841056035 \"}]";





}
