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

public class contactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.contact_fragment, container, false);
        TextView cont = (TextView) rootView.findViewById(R.id.contactText);
        TextView title=(TextView)rootView.findViewById(R.id.textView2);
        title.setTypeface(Utilities.typeface);
        cont.setTypeface(Utilities.typeface);
        title.setText(Utilities.event_name);
        cont.setText(Utilities.event_contact);


        return rootView;
    }
}

