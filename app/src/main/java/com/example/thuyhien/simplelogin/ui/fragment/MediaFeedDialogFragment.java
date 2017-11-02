package com.example.thuyhien.simplelogin.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by thuyhien on 10/26/17.
 */

public class MediaFeedDialogFragment extends DialogFragment {
    private MediaFeed mediaFeed;

    @Inject
    boolean isTablet;

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    @BindView(R.id.text_feed_title)
    TextView textViewFeedTitle;

    @BindView(R.id.text_description)
    TextView textViewDescription;

    @BindView(R.id.layout_header_feed_detail)
    LinearLayout linearLayoutHeaderFeedDetail;

    public static MediaFeedDialogFragment newMediaFeedDialogFragment(MediaFeed mediaFeed) {
        MediaFeedDialogFragment mediaFeedDialogFragment = new MediaFeedDialogFragment();
        Bundle feedBundle = new Bundle();
        feedBundle.putSerializable(MainActivity.MEDIA_FEED, mediaFeed);
        mediaFeedDialogFragment.setArguments(feedBundle);
        return mediaFeedDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((FoxApplication) getActivity().getApplication()).getAppComponent()
                .inject(this);
        getFeedBundle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_media_feed, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isTablet) {
            setDialogLayout();
        }
    }

    @OnClick(R.id.button_close)
    public void onClickCloseButton() {
        dismiss();
    }

    private void getFeedBundle() {
        if (getArguments() != null) {
            mediaFeed = (MediaFeed) getArguments().getSerializable(MainActivity.MEDIA_FEED);
        }
    }

    private void initViews() {
        if (mediaFeed == null) {
            return;
        }

        if (!isTablet) {
            linearLayoutHeaderFeedDetail.setVisibility(View.GONE);
        } else {
            Window window = getDialog().getWindow();
            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE);
            }
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
            Picasso.with(getActivity()).load(Uri.parse(imageUrl))
                    .into(imageViewPoster);
        }
    }

    private void setDialogLayout() {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(((int) getContext().getResources().getDimension(R.dimen.dialog_feed_width)),
                    ViewPager.LayoutParams.WRAP_CONTENT);
        }
    }
}
