package com.example.thuyhien.simplelogin.ui.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.ui.common.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.common.AuthenticationView;
import com.example.thuyhien.simplelogin.ui.main.MainActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements AuthenticationView {

    private AuthenticatePresenter signInPresenter;

    @BindView(R.id.edit_email)
    EditText editTextEmail;

    @BindView(R.id.edit_password)
    EditText editTextPassword;

    @BindString(R.string.hint_email)
    String hintEmail;

    @BindString(R.string.hint_password)
    String hintPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        signInPresenter = new SignInPresenterImpl(this);
    }

    @OnClick(R.id.text_forgot_password)
    public void onClickForgotPassword() {

    }

    @OnClick(R.id.button_login)
    public void onClickLoginButton() {
        AccountRequest accountRequest = new AccountRequest(editTextEmail.getText().toString(),
                editTextPassword.getText().toString());
        signInPresenter.authenticateAccountRequest(accountRequest);
    }

    @OnClick(R.id.button_close)
    public void onClickCloseButton() {
        this.finish();
    }

    @Override
    public void setUsernameError(int error) {
        editTextEmail.setError(getString(error, hintEmail));
    }

    @Override
    public void setPasswordError(int error) {
        editTextPassword.setError(getString(error, hintPassword));
    }

    @Override
    public void navigateToMain() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
        this.finish();
    }

    @Override
    public void showToast(String textMsg) {
        Toast.makeText(this, textMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
