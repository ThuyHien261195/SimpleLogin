package com.example.thuyhien.simplelogin.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.ui.listener.SettingListener;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thuyhien on 11/2/17.
 */

public class LanguageSettingViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.radio_btn_language)
    RadioButton radioButtonLanguage;

    public LanguageSettingViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindContentView(final Language language, final int position,
                                final WeakReference<SettingListener> callBack) {
        radioButtonLanguage.setText(language.getLangName());
        radioButtonLanguage.setOnCheckedChangeListener(null);
        radioButtonLanguage.setChecked(language.isSelected());
        radioButtonLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SettingListener settingListener = callBack.get();
                if (settingListener != null) {
                    settingListener.updateChosenLanguage(language, position);
                }
            }
        });
    }
}
