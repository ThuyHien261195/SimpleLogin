package com.example.thuyhien.simplelogin.view;

import android.graphics.Bitmap;

/**
 * Created by thuyhien on 10/12/17.
 */

public interface PosterView {
    void showPoster(Bitmap bitmap);

    void showErrorLoadPoster(Exception ex);
}
