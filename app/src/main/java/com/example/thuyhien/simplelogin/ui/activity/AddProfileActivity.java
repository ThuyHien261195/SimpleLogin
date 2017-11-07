package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.module.AddProfileModule;
import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProfileActivity extends AppCompatActivity implements AddProfileView {

    private boolean addedProfile = false;

    @BindView(R.id.edit_profile_name)
    EditText editTextProfileName;

    @Inject
    AddProfilePresenter addProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        ((FoxApplication) getApplication()).getAppComponent()
                .createAddProfileComponent(new AddProfileModule(this))
                .inject(this);
        ButterKnife.bind(this);

        displayActionBar();
    }

    @OnClick(R.id.button_add_profile)
    public void onClickAddProfileButton() {
        addProfilePresenter.addProfile(editTextProfileName.getText().toString());
    }

    @Override
    public void showErrorMessage(Exception ex) {
        if (ex instanceof InvalidInputException) {
            String errorCode = ((InvalidInputException) ex).getErrorCode();
            if (errorCode.equals(InvalidInputException.ERROR_CODE_INVALID_NAME)) {
                String errorMsg = getString(R.string.error_empty_field, getString(R.string.hint_profile_name));
                editTextProfileName.setError(errorMsg);
            }
        } else if (ex instanceof AuthenticationException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_add_profile, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayAddedProfile(Profile profile) {
        addedProfile = true;
        Toast.makeText(this, R.string.success_add_profile, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        returnResultProfileIntent();
        super.onBackPressed();
    }

    private void returnResultProfileIntent() {
        if (addedProfile) {
            Intent resultProfileIntent = this.getIntent();
            this.setResult(RESULT_OK, resultProfileIntent);
        }
    }

    private void displayActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
