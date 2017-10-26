package com.example.thuyhien.simplelogin.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.ui.listener.MainActivityListener;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * Created by thuyhien on 10/26/17.
 */

public class LandsPosterViewHolder extends PosterViewHolder {

    @BindView(R.id.text_feed_title)
    TextView textViewFeedTitle;

    private WeakReference<MainActivityListener> pageFragmentListenerWeakRef;

    public LandsPosterViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindImagePoster(MediaFeed mediaFeed, WeakReference<MainActivityListener> pageFragmentListenerWeakRef) {
        super.bindImagePoster(mediaFeed, pageFragmentListenerWeakRef);
        textViewFeedTitle.setText(mediaFeed.getTitle());
        this.pageFragmentListenerWeakRef = pageFragmentListenerWeakRef;
    }

    @Override
    protected void openMediaFeedDetail(View view) {
        if (pageFragmentListenerWeakRef != null) {
            pageFragmentListenerWeakRef.get().onCreateMediaFeedDialog(mediaFeed);
        }
    }
}
