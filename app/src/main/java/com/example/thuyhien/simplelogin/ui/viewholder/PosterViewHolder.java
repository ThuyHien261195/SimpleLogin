package com.example.thuyhien.simplelogin.ui.viewholder;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaImage;
import com.example.thuyhien.simplelogin.utils.ImageUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    private int width, height;
    private final Picasso picasso;

    public PosterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        picasso = Picasso.with(itemView.getContext());

        width = itemView.getContext().getResources().getDimensionPixelSize(ImageUtils.REQUEST_WIDTH);
        height = itemView.getContext().getResources().getDimensionPixelSize(ImageUtils.REQUEST_HEIGHT);
    }

    public void bindImagePoster(MediaImage imagePost) {
        if (imagePost != null && !imagePost.getImageUrl().equals("")) {
            picasso.load(imagePost.getImageUrl())
                    .resize(width, height)
                    .into(imageViewPoster);
        }
    }
}
