package com.example.thuyhien.simplelogin.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.ui.signin.SignInActivity;
import com.example.thuyhien.simplelogin.ui.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;

    @BindView(R.id.button_login)
    Button buttonLogin;

    @BindView(R.id.button_sign_up)
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenterImpl(this);
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

    @Override
    public void setLoggedInView() {
        buttonLogin.setVisibility(View.GONE);
        buttonSignUp.setVisibility(View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void initViews() {
        mainPresenter.checkIsLoggedIn();
    }
}
