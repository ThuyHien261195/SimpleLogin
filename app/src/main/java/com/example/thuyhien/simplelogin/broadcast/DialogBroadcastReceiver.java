package com.example.thuyhien.simplelogin.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
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

        if (action.equals(LandsPosterViewHolder.ACTION_SHOW_FEED_DIALOG)) {
            Bundle bundle = intent.getBundleExtra(PosterViewHolder.BUNDLE_MEDIA_FEED);
            if (bundle == null) {
                return;
            }

            MediaFeed mediaFeed = (MediaFeed) bundle.getSerializable(PosterViewHolder.MEDIA_FEED);
            if (mediaFeed == null) {
                return;
            }

            try {
                if (getMainActivityListener() != null) {
                    getMainActivityListener().onShowMediaFeedDialog(mediaFeed);
                }
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() +
                        context.getString(R.string.error_implement_main_activity_listener));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private MainActivityListener getMainActivityListener() {
        return mainActivityListenerWeakRef.get();
    }
}
