package com.example.thuyhien.simplelogin.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.text_view_term)
    TextView textViewTerm;

    @BindView(R.id.text_view_policy)
    TextView textViewPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        hideActionBar();
        initViews();
    }

    @OnClick(R.id.button_get_started)
    public void onClickGetStartedButton() {
        Intent signUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(signUpIntent);
    }

    @OnClick(R.id.button_sign_in)
    public void onClickSignInButton() {
        Intent signInIntent = new Intent(this, SignInActivity.class);
        startActivity(signInIntent);
    }

    @OnClick(R.id.button_close)
    public void onClickCloseButton() {
        this.finish();
    }

    private void initViews() {
        textViewTerm.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        textViewPolicy.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    private void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}
