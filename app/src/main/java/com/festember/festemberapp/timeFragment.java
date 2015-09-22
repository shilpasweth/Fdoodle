package com.festember.festemberapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.festember.festemberapp.R;
/**
 * Created by Bharath on 17-Sep-15.
 */

public class timeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.time_fragment, container, false);

        TextView title, time,venue;
         time=(TextView)rootView.findViewById(R.id.timeText);
        title=(TextView)rootView.findViewById(R.id.textView2);
        title.setTypeface(Utilities.typeface);
        time.setTypeface(Utilities.typeface);
        //title.setText(Utilities.event_name);
        //desc.setText(Utilities.event_desc);

        return rootView;
    }
}

