package com.example.thuyhien.simplelogin.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Created by thuyhien on 10/13/17.
 */

public class ImageUtils {
    public static final int REQUEST_WIDTH = 100;
    public static final int REQUEST_HEIGHT = 120;

    public static Bitmap decodeBitMapFromInputStream(InputStream inputStream, int outWidth, int outHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = calculateInSampleSize(outWidth, outHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    private static int calculateInSampleSize(int outWidth, int outHeight) {
        int inSampleSize = 1;

        if (outHeight > REQUEST_HEIGHT || outWidth > REQUEST_WIDTH) {
            int halfHeight = outHeight / 2;
            int halfWidth = outWidth / 2;

            while ((halfHeight / inSampleSize) >= REQUEST_HEIGHT
                    && (halfWidth / inSampleSize) >= REQUEST_WIDTH) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
