package com.example.thuyhien.simplelogin.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;

import butterknife.BindView;

/**
 * Created by thuyhien on 10/26/17.
 */

public class LandsPosterViewHolder extends PosterViewHolder {

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
}
