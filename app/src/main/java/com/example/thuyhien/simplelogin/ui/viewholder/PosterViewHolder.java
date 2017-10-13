package com.example.thuyhien.simplelogin.ui.viewholder;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.interactor.LoadDataInteractor;
import com.example.thuyhien.simplelogin.data.interactor.impl.RetrofitLoadDataInteractor;
import com.example.thuyhien.simplelogin.model.ImagePost;
import com.example.thuyhien.simplelogin.presenter.PosterPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.PosterPresenterImpl;
import com.example.thuyhien.simplelogin.view.PosterView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 10/11/17.
 */

public class PosterViewHolder extends RecyclerView.ViewHolder implements PosterView {

    @BindView(R.id.image_poster)
    ImageView imageViewPoster;

    private PosterPresenter posterPresenter;

    public PosterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        createPagePresenter();
    }

    @Override
    public void showPoster(Bitmap bitmap) {
        if (bitmap != null) {
            imageViewPoster.setImageBitmap(bitmap);
        }
    }

    @Override
    public void showErrorLoadPoster(Exception ex) {
        // Keep default image
        // TODO
    }

    public void bindImagePoster(ImagePost imagePost) {
        if (imagePost != null && !imagePost.getImageUrl().equals("")) {
            posterPresenter.loadPoster(imagePost.getImageUrl());
        }
    }

    private void createPagePresenter() {
        LoadDataInteractor loadDataInteractor = new RetrofitLoadDataInteractor();
        posterPresenter = new PosterPresenterImpl(this, loadDataInteractor);
    }
}
