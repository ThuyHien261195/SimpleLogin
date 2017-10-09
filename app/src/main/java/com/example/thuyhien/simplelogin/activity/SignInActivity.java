package com.example.thuyhien.simplelogin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.User;
import com.example.thuyhien.simplelogin.network.AccountRequest;
import com.example.thuyhien.simplelogin.utils.AuthenticationUtils;
import com.example.thuyhien.simplelogin.utils.RetrofitUtils;
import com.example.thuyhien.simplelogin.utils.SharedPreferencesUtils;

import org.json.JSONObject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    @BindView(R.id.edit_email)
    EditText editTextEmail;

    @BindView(R.id.edit_password)
    EditText editTextPassword;

    @BindString(R.string.error_empty_field)
    String errorEmptyField;

    @BindString(R.string.error_invalid_field)
    String errorInvalidField;

    @BindString(R.string.hint_email)
    String hintEmail;

    @BindString(R.string.hint_password)
    String hintPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text_forgot_password)
    public void onClickForgotPassword() {

    }

    @OnClick(R.id.button_login)
    public void onClickLoginButton() {
        boolean checkMail = checkEmailInput();
        boolean checkPass = checkPassInput();
        if (checkMail && checkPass) {
            AccountRequest accountRequest = new AccountRequest(editTextEmail.getText().toString(),
                    editTextPassword.getText().toString());
            Call<User> call = RetrofitUtils.getApiService()
                    .signInAccount(RetrofitUtils.AUTH_TOKEN, accountRequest);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        updateSignUpEmail(response.body());
                        startMainActivity();
                    } else {
                        showToastErrorSignUp(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    showToastErrorSignUp(null);
                    t.printStackTrace();
                }
            });
        }
    }

    @OnClick(R.id.button_close)
    public void onClickCloseButton() {
        this.finish();
    }

    private boolean checkEmailInput() {
        if (!AuthenticationUtils.checkNotEmptyInput(editTextEmail.getText().toString())) {
            editTextEmail.setError(String.format(errorEmptyField, hintEmail));
            return false;
        }
        if (!AuthenticationUtils.checkValidEmail(editTextEmail.getText().toString())) {
            editTextEmail.setError(String.format(errorInvalidField, hintEmail));
            return false;
        }
        return true;
    }

    private boolean checkPassInput() {
        if (!AuthenticationUtils.checkNotEmptyInput(editTextPassword.getText().toString())) {
            editTextPassword.setError(String.format(errorEmptyField, hintPassword));
            return false;
        }
        if (!AuthenticationUtils.checkValidPass(editTextPassword.getText().toString())) {
            editTextPassword.setError(String.format(errorInvalidField, hintPassword));
            return false;
        }
        return true;
    }

    private void updateSignUpEmail(User user) {
        SharedPreferences sharedPref =
                this.getSharedPreferences(SharedPreferencesUtils.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        SharedPreferencesUtils.saveSignedUpEmail(sharedPref, user);
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainIntent);
        this.finish();
    }

    private void showToastErrorSignUp(ResponseBody responseBody) {
        String error = getString(R.string.error_login_user);
        if (responseBody != null) {
            try {
                JSONObject jsonError = new JSONObject(responseBody.string());
                error = jsonError.getString("message");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
