package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.module.AuthenModule;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.view.AuthenticationView;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements AuthenticationView {

    @Inject
    @Named("sign_in_presenter")
    AuthenticatePresenter signInPresenter;

    @BindView(R.id.edit_email)
    EditText editTextEmail;

    @BindView(R.id.edit_password)
    EditText editTextPassword;

    @BindString(R.string.hint_email)
    String hintEmail;

    @BindString(R.string.hint_password)
    String hintPassword;

    @BindString(R.string.error_empty_field)
    String errorEmptyField;

    @BindString(R.string.error_invalid_field)
    String errorInvalidField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((FoxApplication) getApplication()).getAppComponent()
                .createAuthenComponent(new AuthenModule(this))
                .inject(this);

        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text_forgot_password)
    public void onClickForgotPassword() {

    }

    @OnClick(R.id.button_login)
    public void onClickLoginButton() {
        signInPresenter.authenticate(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }

    @OnClick(R.id.button_close)
    public void onClickCloseButton() {
        this.finish();
    }

    @Override
    public void showUsernameError(String errorCode) {
        String errorMsg = "";
        switch (errorCode) {
            case InvalidInputException.ERROR_CODE_EMPTY_EMAIL:
                errorMsg = String.format(errorEmptyField, hintEmail);
                break;
            case InvalidInputException.ERROR_CODE_INVALID_EMAIL:
                errorMsg = String.format(errorInvalidField, hintEmail);
                break;
            default:
                break;
        }
        editTextEmail.setError(errorMsg);
    }

    @Override
    public void showPasswordError(String errorCode) {
        String errorMsg = "";
        switch (errorCode) {
            case InvalidInputException.ERROR_CODE_EMPTY_PASSWORD:
                errorMsg = String.format(errorEmptyField, hintPassword);
                break;
            case InvalidInputException.ERROR_CODE_INVALID_PASSWORD:
                errorMsg = String.format(errorInvalidField, hintPassword);
                break;
            default:
                break;
        }
        editTextPassword.setError(errorMsg);
    }

    @Override
    public void navigateToMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
        this.finish();
    }

    @Override
    public void showMessage(String textMsg) {
        if (!textMsg.equals("")) {
            Toast.makeText(this, textMsg, Toast.LENGTH_SHORT).show();
        }
    }
}
