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

/**
 * Created by thuyhien on 11/6/17.
 */

public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private WeakReference<ProfileActivityListener> listenerWeakReference;
    private Profile profile;

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

        imageButtonDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivityListener listener = getProfileActivityListener();
                if (listener != null) {
                    listener.deleteProfile(profile);
                }
            }
        });
    }

    public void bindContentView(Profile profile, boolean isDeleting) {
        this.profile = profile;
        textViewName.setText(profile.getName());

        if (isDeleting) {
            imageButtonDeleteProfile.setVisibility(View.VISIBLE);
        } else {
            imageButtonDeleteProfile.setVisibility(View.GONE);
        }
    }

    ProfileActivityListener getProfileActivityListener() {
        return listenerWeakReference.get();
    }
}
