package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.ui.listener.ProfileActivityListener;
import com.example.thuyhien.simplelogin.ui.viewholder.AddProfileViewHolder;
import com.example.thuyhien.simplelogin.ui.viewholder.ProfileViewHolder;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 11/6/17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PROFILE_TYPE = 0;
    private static final int ADD_PROFILE_TYPE = 1;

    private final List<Profile> profileList;
    private boolean isDeleting = false;
    private WeakReference<ProfileActivityListener> listenerWeakReference;

    public ProfileAdapter(List<Profile> profileList,
                          ProfileActivityListener listener) {
        this.profileList = profileList;
        listenerWeakReference = new WeakReference<>(listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case PROFILE_TYPE:
                view = inflater.inflate(R.layout.item_profile, parent, false);
                return new ProfileViewHolder(view, listenerWeakReference);
            case ADD_PROFILE_TYPE:
                view = inflater.inflate(R.layout.item_add_profile, parent, false);
                return new AddProfileViewHolder(view, listenerWeakReference);
            default:
                break;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType) {
            case PROFILE_TYPE:
                ((ProfileViewHolder) holder).bindContentView(profileList.get(position), isDeleting);
                break;
            case ADD_PROFILE_TYPE:
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < profileList.size()) {
            return PROFILE_TYPE;
        } else {
            return ADD_PROFILE_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        return profileList.size() + 1;
    }

    public void showDeleteButton(boolean isDeleting) {
        this.isDeleting = isDeleting;
        notifyDataSetChanged();
    }

    public void deleteItem(Profile profile) {
        int pos = profileList.indexOf(profile);
        if (pos != -1) {
            profileList.remove(profile);
            notifyItemRemoved(pos);
        }
    }

    public void addItem(Profile profile) {
        if (profile != null) {
            profileList.add(profile);
            notifyItemChanged(profileList.size() - 1);
        }
    }
}
