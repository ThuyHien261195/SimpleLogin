package com.example.thuyhien.simplelogin.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private boolean isLoggedIn;

    @BindView(R.id.button_login)
    Button buttonLogin;

    @BindView(R.id.button_sign_up)
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getDataSharedPreferences();
        initViews();
    }

    @OnClick(R.id.button_login)
    public void onClickLoginButton() {
        Intent loginIntent = new Intent(this, SignInActivity.class);
        startActivity(loginIntent);
    }

    @OnClick(R.id.button_sign_up)
    public void onClickSignUpButton() {
        Intent loginIntent = new Intent(this, SignUpActivity.class);
        startActivity(loginIntent);
    }

    private void getDataSharedPreferences() {
        sharedPref = this.getSharedPreferences(SharedPreferencesUtils.PREF_DATA_FILE_NAME, MODE_PRIVATE);
        isLoggedIn = SharedPreferencesUtils.isLoggedIn(sharedPref);
    }

    private void initViews() {
        if (isLoggedIn) {
            buttonLogin.setVisibility(View.GONE);
            buttonSignUp.setVisibility(View.GONE);
        }
    }
}
