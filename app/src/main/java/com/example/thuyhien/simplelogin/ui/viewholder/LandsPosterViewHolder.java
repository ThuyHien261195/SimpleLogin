package com.example.thuyhien.simplelogin.ui.viewholder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;

import butterknife.BindView;

/**
 * Created by thuyhien on 10/26/17.
 */

public class LandsPosterViewHolder extends PosterViewHolder {

    public static final String ACTION_SHOW_FEED_DIALOG = "com.example.thuyhien.simplelogin.SHOW_FEED_DIALOG";

    @BindView(R.id.text_feed_title)
    TextView textViewFeedTitle;

    public LandsPosterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindImagePoster(MediaFeed mediaFeed) {
        super.bindImagePoster(mediaFeed);
        textViewFeedTitle.setText(mediaFeed.getTitle());
    }

    @Override
    protected void openMediaFeedDetail(View view, Bundle feedBundle) {
        Intent feedDialogIntent = new Intent();
        feedDialogIntent.setAction(ACTION_SHOW_FEED_DIALOG);
        feedDialogIntent.putExtra(BUNDLE_MEDIA_FEED, feedBundle);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(view.getContext());
        localBroadcastManager.sendBroadcast(feedDialogIntent);
    }
}
