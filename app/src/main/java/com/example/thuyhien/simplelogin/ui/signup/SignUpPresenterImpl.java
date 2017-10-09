package com.example.thuyhien.simplelogin.ui.signup;

import android.content.SharedPreferences;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.database.User;
import com.example.thuyhien.simplelogin.data.network.AuthenticationApiHelper;
import com.example.thuyhien.simplelogin.data.network.model.AccountRequest;
import com.example.thuyhien.simplelogin.data.prefs.SharedPreferencesHelper;
import com.example.thuyhien.simplelogin.listener.OnAuthenticateAccountListener;
import com.example.thuyhien.simplelogin.ui.common.AuthenticatePresenter;
import com.example.thuyhien.simplelogin.ui.common.AuthenticationView;
import com.example.thuyhien.simplelogin.utils.AuthenticationUtils;

import org.json.JSONObject;

import okhttp3.ResponseBody;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by thuyhien on 10/9/17.
 */

public class SignUpPresenterImpl implements AuthenticatePresenter, OnAuthenticateAccountListener {

    private AuthenticationView signUpView;
    private AuthenticationApiHelper signUpInteractor;

    public SignUpPresenterImpl(AuthenticationView signUpView) {
        this.signUpView = signUpView;
        signUpInteractor = new AuthenticationApiHelper();
    }

    @Override
    public boolean validateAccountRequest(AccountRequest accountRequest) {
        boolean validate = true;
        if (!AuthenticationUtils.checkNotEmptyInput(accountRequest.getEmail())) {
            validate = false;
            signUpView.setUsernameError(R.string.error_empty_field);
        } else if (!AuthenticationUtils.checkValidEmail(accountRequest.getEmail())) {
            validate = false;
            signUpView.setUsernameError(R.string.error_invalid_field);
        }

        if (!AuthenticationUtils.checkNotEmptyInput(accountRequest.getPassword())) {
            validate = false;
            signUpView.setPasswordError(R.string.error_empty_field);
        } else if (!AuthenticationUtils.checkValidPass(accountRequest.getPassword())) {
            validate = false;
            signUpView.setPasswordError(R.string.error_invalid_field);
        }

        return validate;
    }

    @Override
    public void authenticateAccountRequest(AccountRequest accountRequest) {
        boolean validate = validateAccountRequest(accountRequest);
        if (validate) {
            signUpInteractor.signUp(accountRequest, this);
        }
    }

    @Override
    public void updateAuthenticationEmail(User user) {
        SharedPreferences sharedPref = signUpView.getContext().getSharedPreferences(
                SharedPreferencesHelper.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        SharedPreferencesHelper.saveSignedUpEmail(sharedPref, user);
    }

    @Override
    public void onAuthenticateSuccess(User user) {
        updateAuthenticationEmail(user);
        signUpView.navigateToMain();
    }

    @Override
    public void onAuthenticateFail(ResponseBody responseBody) {
        String error = signUpView.getContext().getString(R.string.error_create_user);
        if (responseBody != null) {
            try {
                JSONObject jsonError = new JSONObject(responseBody.string());
                error = jsonError.getString("message");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        signUpView.showToast(error);
    }
}
