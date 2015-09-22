package com.festember.festemberapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Created by HP on 11-09-2015.
 */
public class Bitmaphandle {
    public static LruCache<String, Bitmap> mMemoryCache;
    public static int maxMemory=0;

    // Use 1/8th of the available memory for this memory cache.
    public static int  cacheSize=0;

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //Log.e("returnbit","*");
        return BitmapFactory.decodeResource(res, resId, options);

    }
    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        //if (getBitmapFromMemCache(key) == null)
        {
            Bitmap b=Bitmaphandle.mMemoryCache.put(key, bitmap);
            if(b!=null)
                Log.e("ll","added nnotnull");
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return Bitmaphandle.mMemoryCache.get(key);
    }
    public static void loadBitmap(int resId, ImageView imageView) {

        final String imageKey = String.valueOf(resId);
Log.e("ll",imageKey);
        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap != null) {
            Log.e("ll","oadnotnull");
            imageView.setImageBitmap(bitmap);
        }
        else
        {
            imageView.setBackgroundResource(resId);
        }
    }


}
