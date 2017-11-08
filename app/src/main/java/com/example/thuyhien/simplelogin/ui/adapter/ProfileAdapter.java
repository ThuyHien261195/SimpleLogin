package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v4.util.Pair;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thuyhien on 11/6/17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PROFILE_TYPE = 0;
    private static final int ADD_PROFILE_TYPE = 1;

    private List<Pair<Profile, Boolean>> profileList = new ArrayList<>();
    private boolean isDeleting = false;
    private WeakReference<ProfileActivityListener> listenerWeakReference;

    public ProfileAdapter(List<Profile> profileList,
                          ProfileActivityListener listener) {
        listenerWeakReference = new WeakReference<>(listener);
        for (Profile profile : profileList) {
            this.profileList.add(new Pair<>(profile, false));
        }
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
                ((ProfileViewHolder) holder).bindContentView(profileList.get(position).first,
                        isDeleting, profileList.get(position).second);
                break;
            case ADD_PROFILE_TYPE:
                ((AddProfileViewHolder) holder).bindContentView(isDeleting);
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
        if (profile == null) {
            return;
        }
        int pos = findPositionProfileList(profile);
        if (pos != -1) {
            profileList.remove(pos);
            notifyItemRemoved(pos);
        }
    }

    public void addItem(Profile profile) {
        if (profile != null) {
            profileList.add(new Pair<Profile, Boolean>(profile, false));
            notifyItemChanged(profileList.size() - 1);
        }
    }

    public void updateSelectedItem(Profile profile, boolean isSelecting) {
        int pos = findPositionProfileList(profile);
        if (pos != -1) {
            Pair<Profile, Boolean> profileBooleanPair =
                    new Pair<>(profileList.get(pos).first, isSelecting);
            profileList.set(pos, profileBooleanPair);
            notifyItemChanged(pos);
        }
    }

    public void clearSelectedProfileList() {
        for (int i = 0; i < profileList.size(); i++) {
            if (profileList.get(i).second) {
                profileList.set(i, new Pair<Profile, Boolean>(profileList.get(i).first, false));
            }
        }
        notifyDataSetChanged();
    }

    private int findPositionProfileList(Profile profile) {
        for (int i = 0; i < profileList.size(); i++) {
            if (profileList.get(i).first == profile) {
                return i;
            }
        }
        return -1;
    }
}
