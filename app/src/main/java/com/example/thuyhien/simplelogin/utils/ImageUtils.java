package com.example.thuyhien.simplelogin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;

import java.io.InputStream;

/**
 * Created by thuyhien on 10/13/17.
 */

public class ImageUtils {
    public static final int REQUEST_WIDTH = R.dimen.item_poster_width;
    public static final int REQUEST_HEIGHT = R.dimen.item_poster_height;

    public static Bitmap decodeBitMapFromInputStream(InputStream inputStream,
                                                     int outWidth,
                                                     int outHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = calculateInSampleSize(outWidth, outHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    private static int calculateInSampleSize(int outWidth, int outHeight) {
        int inSampleSize = 1;

        int reqWidth = FoxApplication.getInstance().getResources().getDimensionPixelSize(REQUEST_WIDTH);
        int reqHeight = FoxApplication.getInstance().getResources().getDimensionPixelSize(REQUEST_HEIGHT);
        if (outHeight > reqHeight || outWidth > reqWidth) {
            int halfHeight = outHeight / 2;
            int halfWidth = outWidth / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
