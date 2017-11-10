package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.ui.listener.ProfileActivityListener;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 11/6/17.
 */

public class AddProfileViewHolder extends RecyclerView.ViewHolder {

    private WeakReference<ProfileActivityListener> listenerWeakReference;

    @BindView(R.id.image_button_add_profile)
    ImageButton imageButtonAddProfile;

    public AddProfileViewHolder(View itemView, final ProfileActivityListener listenerWeakReference) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.listenerWeakReference = new WeakReference<>(listenerWeakReference);
        imageButtonAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getProfileActivityListener() != null) {
                    getProfileActivityListener().openAddProfileScreen();
                }
            }
        });
    }

    public void bindContentView(boolean isDeleting) {
        imageButtonAddProfile.setVisibility(isDeleting ? View.GONE : View.VISIBLE);
    }

    private ProfileActivityListener getProfileActivityListener() {
        return listenerWeakReference.get();
    }
}
