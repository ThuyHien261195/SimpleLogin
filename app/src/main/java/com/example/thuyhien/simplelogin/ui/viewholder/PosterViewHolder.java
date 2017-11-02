package com.example.thuyhien.simplelogin.ui.viewholder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.ui.activity.MediaFeedActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    protected MediaFeed mediaFeed;
    private final Picasso picasso;

    public PosterViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        picasso = Picasso.with(itemView.getContext());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle feedBundle = createFeedBundle();
                openMediaFeedDetail(view, feedBundle);
            }
        });
    }

    public void bindImagePoster(MediaFeed mediaFeed) {
        this.mediaFeed = mediaFeed;
        List<MediaImage> thumbnails = mediaFeed.getThumbnails();
        if (thumbnails != null && thumbnails.size() > 0) {
            MediaImage imagePost = thumbnails.get(0);
            if (imagePost != null && !imagePost.getImageUrl().equals("")) {
                String imageUrl = imagePost.getImageUrl().replace(" ", "%20");
                picasso.load(Uri.parse(imageUrl))
                        .fit()
                        .centerCrop()
                        .into(imageViewPoster);
            }
        }
    }

    private void openMediaFeedDetail(View view, Bundle feedBundle) {
        Intent feedIntent = new Intent(view.getContext(), MediaFeedActivity.class);
        feedIntent.setAction(MainActivity.ACTION_SHOW_FEED_DETAIL);
        feedIntent.putExtra(MainActivity.BUNDLE_MEDIA_FEED, feedBundle);
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(view.getContext());
        localBroadcastManager.sendBroadcast(feedIntent);
    }

    private Bundle createFeedBundle() {
        Bundle feedBundle = new Bundle();
        feedBundle.putSerializable(MainActivity.MEDIA_FEED, mediaFeed);
        return feedBundle;
    }
}
