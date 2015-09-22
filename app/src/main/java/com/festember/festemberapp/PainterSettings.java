package com.festember.festemberapp;

import android.content.pm.ActivityInfo;

public class PainterSettings {
	public BrushPreset preset = null;
    public static boolean mir=true;
    public static boolean miry=false;

	public String lastPicture = null;
	public boolean forceOpenFile = false;
	public int orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
}