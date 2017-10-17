package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    private final Picasso picasso;

    public PosterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        picasso = Picasso.with(itemView.getContext());
    }

    public void bindImagePoster(MediaImage imagePost) {
        if (imagePost != null && !imagePost.getImageUrl().equals("")) {
            picasso.load(imagePost.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(imageViewPoster);
        }
    }
}
