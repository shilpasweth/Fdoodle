package com.festember.festemberapp;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatSpinner;

public class BlurStyleSpinner extends AppCompatSpinner {

	public BlurStyleSpinner(Context context) {
		super(context);
	}

	public BlurStyleSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public BlurStyleSpinner(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Painter painter = (Painter) getContext();
        painter.resetPresets();
        super.onClick(dialog, which);
    }
}