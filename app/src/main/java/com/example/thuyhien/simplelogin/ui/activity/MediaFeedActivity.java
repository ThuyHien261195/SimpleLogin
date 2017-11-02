package com.example.thuyhien.simplelogin.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;

import butterknife.ButterKnife;

public class MediaFeedActivity extends AppCompatActivity {

    private MediaFeed mediaFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_feed);

        ButterKnife.bind(this);
        getMediaFeedBundle();
        initViews();
    }

    private void initViews() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MediaFeedDialogFragment mediaFeedDialogFragment = MediaFeedDialogFragment
                .newMediaFeedDialogFragment(mediaFeed);
        fragmentTransaction.add(R.id.layout_feed_detail, mediaFeedDialogFragment);
        fragmentTransaction.commit();
    }

    public void getMediaFeedBundle() {
        Bundle feedBundle = getIntent().getBundleExtra(MainActivity.BUNDLE_MEDIA_FEED);
        if (feedBundle != null) {
            mediaFeed = (MediaFeed) feedBundle.getSerializable(MainActivity.MEDIA_FEED);
        }
    }
}
