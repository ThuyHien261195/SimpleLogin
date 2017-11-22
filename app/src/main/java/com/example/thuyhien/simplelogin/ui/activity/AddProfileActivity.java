package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.network.exception.LoadProfileException;
import com.example.thuyhien.simplelogin.model.Profile;
import com.example.thuyhien.simplelogin.presenter.AddProfilePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.view.AddProfileView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class AddProfileActivity extends AppCompatActivity implements AddProfileView {

    public static final String BUNDLE_PROFILE = "BundleProfile";
    public static final String EXTRA_ADDED_PROFILE = "ExtraAddedProfile";

    @Inject
    AddProfilePresenter addProfilePresenter;

    @BindView(R.id.edit_profile_name)
    EditText editTextProfileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

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
                String errorMsg = getString(R.string.error_empty_field,
                        getString(R.string.hint_profile_name));
                editTextProfileName.setError(errorMsg);
            }
        } else if (ex instanceof LoadProfileException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_add_profile, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void displayAddedProfile(Profile profile) {
        Toast.makeText(this, R.string.success_add_profile, Toast.LENGTH_SHORT).show();

        Intent resultProfileIntent = this.getIntent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ADDED_PROFILE, profile);
        resultProfileIntent.putExtra(BUNDLE_PROFILE, bundle);
        this.setResult(RESULT_OK, resultProfileIntent);
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
