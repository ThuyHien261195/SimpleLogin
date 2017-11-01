package com.example.thuyhien.simplelogin.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager.DEFAULT_LANGUAGE_CODE;

public class SettingsActivity extends AppCompatActivity{

    @Inject
    SettingsPresenter settingsPresenter;

    @Inject
    HashMap<String, String> languageList;

    @BindView(R.id.text_chosen_lang)
    TextView textViewChosenLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((FoxApplication) getApplication()).getAppComponent()
                .createSettingsComponent()
                .inject(this);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }

    }

    @OnClick(R.id.layout_language)
    public void onClickLayoutLanguage() {
        Collection<String> collection = languageList.keySet();
        final String[] languageNameList = collection.toArray(new String[collection.size()]);
        final int currentLangIndex = findIndexLanguageCode();

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.title_dialog_language)
                .setSingleChoiceItems(languageNameList,
                        currentLangIndex, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                .setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int selectedPost = ((AlertDialog) dialogInterface).getListView()
                                .getCheckedItemPosition();
                        if (selectedPost != currentLangIndex) {
                            FoxApplication.langCode = languageList.get(languageNameList[selectedPost]);
                            settingsPresenter.saveChosenLanguage();
                        } else {
                            dialogInterface.dismiss();
                        }
                    }
                })
                .setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int findIndexLanguageCode() {
        List<String> languageCodeList = new ArrayList<>(languageList.values());
        int index = languageCodeList.indexOf(FoxApplication.langCode);
        if (index != -1) {
            return index;
        }
        return languageCodeList.indexOf(DEFAULT_LANGUAGE_CODE);
    }
}
