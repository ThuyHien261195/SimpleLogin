package com.example.thuyhien.simplelogin.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.ui.listener.MainActivityListener;
import com.example.thuyhien.simplelogin.ui.viewholder.PosterViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by thuyhien on 10/26/17.
 */

public class MediaFeedDialogFragment extends DialogFragment {

    private MainActivityListener mainActivityListener;
    private MediaFeed mediaFeed;

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    @BindView(R.id.text_feed_title)
    TextView textViewFeedTitle;

    @BindView(R.id.text_description)
    TextView textViewDescription;

    public static MediaFeedDialogFragment newMediaFeedDialogFragment(MediaFeed mediaFeed) {
        MediaFeedDialogFragment mediaFeedDialogFragment = new MediaFeedDialogFragment();
        Bundle feedBundle = new Bundle();
        feedBundle.putSerializable(PosterViewHolder.MEDIA_FEED, mediaFeed);
        mediaFeedDialogFragment.setArguments(feedBundle);
        return mediaFeedDialogFragment;
    }

    @Override
    public void onAttach(Context context) {
        try {
            mainActivityListener = (MainActivityListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + getString(R.string.error_implement_main_activity_listener));
        }
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_media_feed, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        initViews();
        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFeedBundle();
    }

    private void getFeedBundle() {
        if (getArguments() != null) {
            mediaFeed = (MediaFeed) getArguments().getSerializable(PosterViewHolder.MEDIA_FEED);
        }
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
            Picasso.with(getActivity()).load(Uri.parse(imageUrl))
                    .into(imageViewPoster);
        }
    }

    @Override
    public void onDetach() {
        mainActivityListener = null;
        super.onDetach();
    }

    @OnClick(R.id.button_close)
    public void onClickCloseButton() {
        if (mainActivityListener != null) {
            mainActivityListener.onCloseMediaFeedDialog();
        }
    }
}
