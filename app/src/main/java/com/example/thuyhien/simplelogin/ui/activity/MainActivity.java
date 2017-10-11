package com.example.thuyhien.simplelogin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.manager.UserManager;
import com.example.thuyhien.simplelogin.data.manager.impl.SharedPreferencesUserManager;
import com.example.thuyhien.simplelogin.presenter.MainPresenter;
import com.example.thuyhien.simplelogin.presenter.impl.MainPresenterImpl;
import com.example.thuyhien.simplelogin.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView,
    NavigationView.OnNavigationItemSelectedListener{

    private MainPresenter mainPresenter;
    private FoxApplication foxApplication = FoxApplication.getInstance();

    @BindView(R.id.button_login)
    Button buttonLogin;

    @BindView(R.id.button_sign_up)
    Button buttonSignUp;

    @BindView(R.id.drawer_layout_main)
    DrawerLayout drawerLayoutMain;

    @BindView(R.id.nav_view_main)
    NavigationView navigationViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        createMainPresenter();
        initViews();
        mainPresenter.checkIsLoggedIn();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayoutMain.isDrawerOpen(GravityCompat.START)) {
            drawerLayoutMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.button_login)
    public void onClickLoginButton() {
        Intent signInIntent = new Intent(this, SignInActivity.class);
        startActivity(signInIntent);
    }

    @OnClick(R.id.button_sign_up)
    public void onClickSignUpButton() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    @Override
    public void showLoggedInView() {
        buttonLogin.setVisibility(View.GONE);
        buttonSignUp.setVisibility(View.GONE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
        }
        return true;
    }

    private void createMainPresenter() {
        UserManager userManager = new SharedPreferencesUserManager(foxApplication.getSharedPref());
        mainPresenter = new MainPresenterImpl(this, userManager);
    }

    private void initViews() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayoutMain, R.string.navigation_drawer_open, R.string.description_close_image);
        drawerLayoutMain.addDrawerListener(toggle);
        toggle.syncState();
        navigationViewMain.setNavigationItemSelectedListener(this);
    }
}
