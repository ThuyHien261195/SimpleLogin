package com.example.thuyhien.simplelogin.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.ui.viewholder.PosterViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaFeedActivity extends AppCompatActivity {

    private MediaFeed mediaFeed;

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    @BindView(R.id.text_feed_title)
    TextView textViewFeedTitle;

    @BindView(R.id.text_description)
    TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_feed);

        ButterKnife.bind(this);
        getMediaFeedBundle();
        initViews();
    }

    private void initViews() {
        if (mediaFeed == null) {
            return;
        }

        textViewFeedTitle.setText(mediaFeed.getTitle());
        textViewDescription.setText(mediaFeed.getDescription());

        List<MediaImage> thumbnails = mediaFeed.getThumbnails();
        if (thumbnails == null || thumbnails.size() == 0) {
            return;
        }

        MediaImage imagePoster = thumbnails.get(0);
        if (imagePoster != null && !imagePoster.getImageUrl().equals("")) {
            String imageUrl = imagePoster.getImageUrl().replace(" ", "%20");
            Picasso.with(this).load(Uri.parse(imageUrl))
                    .into(imageViewPoster);
        }
    }

    public void getMediaFeedBundle() {
        Bundle feedBundle = getIntent().getBundleExtra(PosterViewHolder.BUNDLE_MEDIA_FEED);
        if (feedBundle != null) {
            mediaFeed = (MediaFeed) feedBundle.getSerializable(PosterViewHolder.MEDIA_FEED);
        }
    }
}
