package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesUserManager;
import com.example.thuyhien.simplelogin.ui.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.ui.presenter.impl.MainPresenterImpl;
import com.example.thuyhien.simplelogin.ui.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;
    private FoxApplication foxApplication = FoxApplication.getInstance();

    @BindView(R.id.button_login)
    Button buttonLogin;

    @BindView(R.id.button_sign_up)
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createMainPresenter();
        mainPresenter.checkIsLoggedIn();
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
    public void showLoggedInView() {
        buttonLogin.setVisibility(View.GONE);
        buttonSignUp.setVisibility(View.GONE);
    }

    private void createMainPresenter() {
        UserManager userManager = new SharedPreferencesUserManager(foxApplication.getSharedPref());
        mainPresenter = new MainPresenterImpl(this, userManager);
    }
}
