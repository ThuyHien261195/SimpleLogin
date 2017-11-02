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
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesAppManager.DEFAULT_LANGUAGE_CODE;

public class SettingsActivity extends AppCompatActivity {

    private String oldLanguageCode = FoxApplication.langCode;
    @Inject
    SettingsPresenter settingsPresenter;

    @Inject
    LinkedHashMap<String, String> languageList;

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

        displayChosenLanguage();
    }

    @OnClick(R.id.layout_language)
    public void onClickLayoutLanguage() {
        List<String> languageNameList = new ArrayList<>(languageList.values());
        final List<String> languageCodeList = new ArrayList<>(languageList.keySet());
        final int currentLangIndex = findIndexLanguageCode();

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.title_dialog_language)
                .setSingleChoiceItems(languageNameList.toArray(new String[languageNameList.size()]),
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
                            FoxApplication.langCode = languageCodeList.get(selectedPost);
                            settingsPresenter.saveChosenLanguage();
                            displayChosenLanguage();
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void displayChosenLanguage() {
        String langName = languageList.get(FoxApplication.langCode);
        textViewChosenLang.setText(langName);
    }

    private int findIndexLanguageCode() {
        List<String> languageCodeList = new ArrayList<>(languageList.keySet());
        int index = languageCodeList.indexOf(FoxApplication.langCode);
        if (index != -1) {
            return index;
        }
        return languageCodeList.indexOf(DEFAULT_LANGUAGE_CODE);
    }

    @Override
    public void onBackPressed() {
        if (!oldLanguageCode.equals(FoxApplication.langCode)) {
            setResult(RESULT_OK);
        }
        super.onBackPressed();
    }
}
