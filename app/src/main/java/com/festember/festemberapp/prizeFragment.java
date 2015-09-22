package com.festember.festemberapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.festember.festemberapp.R;
/**
 * Created by Bharath on 17-Sep-15.
 */

public class prizeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.prize_fragment, container, false);

        return rootView;
    }
}

