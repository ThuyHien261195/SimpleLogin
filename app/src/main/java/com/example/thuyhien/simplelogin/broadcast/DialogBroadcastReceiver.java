package com.example.thuyhien.simplelogin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.example.thuyhien.simplelogin.ui.adapter.PosterAdapter;
import com.example.thuyhien.simplelogin.ui.listener.MainActivityListener;
import com.example.thuyhien.simplelogin.ui.viewholder.LandsPosterViewHolder;
import com.example.thuyhien.simplelogin.ui.viewholder.PosterViewHolder;

import java.lang.ref.WeakReference;

/**
 * Created by thuyhien on 10/27/17.
 */

public class DialogBroadcastReceiver extends BroadcastReceiver {

    private WeakReference<MainActivityListener> mainActivityListenerWeakRef;

    public DialogBroadcastReceiver(MainActivityListener mainActivityListener) {
        mainActivityListenerWeakRef = new WeakReference<MainActivityListener>(mainActivityListener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(MainActivity.ACTION_SHOW_FEED_DETAIL)) {
            Bundle feedBundle = intent.getBundleExtra(MainActivity.BUNDLE_MEDIA_FEED);
            if (feedBundle == null) {
                return;
            }

            if (getMainActivityListener() != null) {
                getMainActivityListener().onShowMediaFeedDetail(feedBundle);
            }
        }
    }

    private MainActivityListener getMainActivityListener() {
        return mainActivityListenerWeakRef.get();
    }
}
