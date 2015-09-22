package com.festember.festemberapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.festember.festemberapp.R;
/**
 * Created by Bharath on 17-Sep-15.
 */
public class introFragment extends Fragment {
    TextView desc,title;
Button rb;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.intro_fragment, container, false);
        desc=(TextView)rootView.findViewById(R.id.introText);
        title=(TextView)rootView.findViewById(R.id.textView2);
        title.setTypeface(Utilities.typeface);
        desc.setTypeface(Utilities.typeface);
        title.setText(Utilities.event_name);
        desc.setText(Utilities.event_desc);
        rb=(Button)rootView.findViewById(R.id.regbutton);
        rb.setTypeface(Utilities.typeface);


        return rootView;
    }
}

