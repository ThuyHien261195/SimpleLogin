package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.module.SettingsModule;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.view.SettingsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements SettingsView {

    @Inject
    SettingsPresenter settingsPresenter;

    @BindView(R.id.radio_group_language)
    RadioGroup radioGroupLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((FoxApplication) getApplication()).getAppComponent()
                .createSettingsComponent(new SettingsModule(this))
                .inject(this);
        ButterKnife.bind(this);

        settingsPresenter.getLanguageList();
    }

    @Override
    public void showLanguageList(final List<Language> languageList, String usedLanguageCode) {
        for (int i = 0; i < languageList.size(); i++) {
            Language language = languageList.get(i);
            View view = createRadioButtonLanguage(usedLanguageCode, i, language);
            radioGroupLanguage.addView(view);
        }

        radioGroupLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                settingsPresenter.saveChosenLanguage(languageList.get(i));
            }
        });
    }

    View createRadioButtonLanguage(String usedLanguageCode, int i, Language language) {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.item_language, radioGroupLanguage, false);
        RadioButton radioButtonLanguage = (RadioButton) view;
        radioButtonLanguage.setId(i);
        radioButtonLanguage.setText(language.getLangName());

        if (language.getLangCode().equals(usedLanguageCode)) {
            radioButtonLanguage.setChecked(true);
        } else {
            radioButtonLanguage.setChecked(false);
        }
        return view;
    }

    @Override
    public void reloadAppAfterChangeLanguage() {
        refreshApp();
    }

    private void refreshApp() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        Intent upIntent = NavUtils.getParentActivityIntent(this);
//        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
//            TaskStackBuilder.create(this)
//                    .addNextIntentWithParentStack(upIntent)
//                    .startActivities();
//            Log.e("NavUtils", "Not part");
//        } else {
//            NavUtils.navigateUpTo(this, upIntent);
//            Log.e("NavUtils", "Part");
//        }
    }
}
