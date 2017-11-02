package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.ui.listener.SettingListener;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by thuyhien on 11/2/17.
 */

public class LanguageSettingAdapter extends RecyclerView.Adapter<LanguageSettingViewHolder> {

    private List<Language> languageList;
    private WeakReference<SettingListener> settingListenerWeakRef;

    public LanguageSettingAdapter(List<Language> languageList,
                                  SettingListener settingListener) {
        this.languageList = languageList;
        this.settingListenerWeakRef = new WeakReference<SettingListener>(settingListener);
    }

    @Override
    public LanguageSettingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_language, parent, false);
        return new LanguageSettingViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(LanguageSettingViewHolder holder, int position) {
        holder.bindContentView(languageList.get(position), position, settingListenerWeakRef);
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public void updateChosenLanguage(int chosenLangPos) {
        for (int i = 0; i < languageList.size(); i++) {
            if (i != chosenLangPos) {
                languageList.get(i).setSelected(false);
            } else {
                languageList.get(i).setSelected(true);
            }
        }
        notifyDataSetChanged();
    }
}
