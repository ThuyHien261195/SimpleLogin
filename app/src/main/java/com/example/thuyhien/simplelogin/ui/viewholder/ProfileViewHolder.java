package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.ui.listener.ProfileActivityListener;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by thuyhien on 11/6/17.
 */

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private WeakReference<ProfileActivityListener> listenerWeakReference;
    private Profile profile;
    private boolean isSelecting = false;

    @BindView(R.id.image_profile)
    ImageView imageProfile;

    @BindView(R.id.image_button_delete_profile)
    ImageButton imageButtonDeleteProfile;

    @BindView(R.id.text_name)
    TextView textViewName;

    public ProfileViewHolder(View itemView, final WeakReference<ProfileActivityListener> listenerWeakReference) {
        super(itemView);
        this.listenerWeakReference = listenerWeakReference;
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.image_button_delete_profile)
    public void onClickDeleteProfile() {
        if (getProfileActivityListener() != null) {
            getProfileActivityListener().updateSelectDeletedProfile(profile);
        }
    }

    @OnLongClick(R.id.image_profile)
    public boolean onLongClickProfile() {
        if (getProfileActivityListener() != null) {
            getProfileActivityListener().enableDeleteProfileMode(profile);
        }
        return true;
    }

    public void bindContentView(Profile profile, boolean isDeleting, boolean isSelecting) {
        this.profile = profile;
        textViewName.setText(profile.getName());

        imageButtonDeleteProfile.setVisibility(isDeleting ? View.VISIBLE : View.GONE);
        imageButtonDeleteProfile.setImageResource(isSelecting ?
                R.mipmap.ic_done_white_24dp : R.mipmap.ic_highlight_off_white_24dp);
    }

    private ProfileActivityListener getProfileActivityListener() {
        return listenerWeakReference.get();
    }
}
