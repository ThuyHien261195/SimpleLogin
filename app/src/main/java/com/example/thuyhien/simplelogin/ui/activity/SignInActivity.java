package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.dagger.component.AppComponent;
import com.example.thuyhien.simplelogin.dagger.component.AuthenComponent;
import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.data.network.exception.FacebookAuthenticationException;
import com.example.thuyhien.simplelogin.presenter.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.exception.InvalidInputException;
import com.example.thuyhien.simplelogin.view.AuthenticationView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity implements AuthenticationView {

    public static final String PERMISSION_EMAIL = "email";
    private CallbackManager callbackManager;
    private AuthenComponent authenComponent;

    @Inject
    @Named("sign_in_presenter")
    AuthenticatePresenter signInPresenter;

    @BindView(R.id.edit_email)
    EditText editTextEmail;

    @BindView(R.id.edit_password)
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent appComponent = ((FoxApplication) getApplication()).getAppComponent();
        authenComponent = appComponent.authenBuilder()
                .bindsAuthenActivity(this)
                .build();
        authenComponent.inject(this);

        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        registerFacebookCallback();
    }

    @BindString(R.string.hint_email)
    String hintEmail;

    @BindString(R.string.hint_password)
    String hintPassword;

    @BindString(R.string.error_empty_field)
    String errorEmptyField;

    @BindString(R.string.error_invalid_field)
    String errorInvalidField;

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
    public void showErrorMessage(Exception ex) {
        if (ex instanceof FacebookAuthenticationException) {
            LoginManager.getInstance().logOut();
        }

        if (ex instanceof AuthenticationException
                || ex instanceof FacebookAuthenticationException) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.error_unknown, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.button_login_facebook)
    public void onClickLoginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this,
                Collections.singletonList(PERMISSION_EMAIL));
    }

    private void registerFacebookCallback() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                if (accessToken.getPermissions().contains(PERMISSION_EMAIL)) {
                    signInPresenter.authenticateFacebookAcc(accessToken.getToken());
                } else {
                    LoginManager.getInstance().logOut();
                    Toast.makeText(getApplicationContext(), R.string.request_access_email,
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login_facebook,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login_facebook,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
