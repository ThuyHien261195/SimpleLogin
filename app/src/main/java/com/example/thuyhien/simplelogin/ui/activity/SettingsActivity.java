package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.module.SettingsModule;
import com.example.thuyhien.simplelogin.model.Language;
import com.example.thuyhien.simplelogin.presenter.SettingsPresenter;
import com.example.thuyhien.simplelogin.ui.adapter.LanguageSettingAdapter;
import com.example.thuyhien.simplelogin.ui.listener.SettingListener;
import com.example.thuyhien.simplelogin.view.SettingsView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements SettingsView, SettingListener {

    @Inject
    SettingsPresenter settingsPresenter;

    @BindView(R.id.recycler_view_language)
    RecyclerView recyclerViewLanguage;
    private LanguageSettingAdapter languageAdapter;

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
    public void showLanguageList(List<Language> languageList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewLanguage.setLayoutManager(linearLayoutManager);
        languageAdapter = new LanguageSettingAdapter(languageList, this);
        recyclerViewLanguage.setAdapter(languageAdapter);
    }

    @Override
    public void updateChosenLanguage(Language chosenLang, int chosenLangPos) {
        languageAdapter.updateChosenLanguage(chosenLangPos);
        settingsPresenter.saveChosenLanguage(chosenLang.getLangCode());
    }

    @Override
    public void onBackPressed() {
        refreshPreviousActivity();
        super.onBackPressed();
    }

    private void refreshPreviousActivity() {
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(upIntent)
                    .startActivities();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
    }
}
