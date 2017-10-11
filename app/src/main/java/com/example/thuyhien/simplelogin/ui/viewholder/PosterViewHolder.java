package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.ImagePost;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.recycler_view_poster)
    RecyclerView recyclerViewPoster;

    private ImagePost imagePost;

    public PosterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindImagePoster(ImagePost imagePost) {
        this.imagePost = imagePost;
    }
}
