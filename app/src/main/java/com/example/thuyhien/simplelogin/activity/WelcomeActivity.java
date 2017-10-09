package com.example.thuyhien.simplelogin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.example.thuyhien.simplelogin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.text_view_info_app)
    TextView textViewInfoApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

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
        textViewInfoApp.setText(createSpannableStringInfoApp());
    }

    private SpannableString createSpannableStringInfoApp() {
        SpannableString termsText = new SpannableString(getText(R.string.title_view_terms));
        termsText.setSpan(new URLSpan(""), 0, termsText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        termsText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.white)),
                0, termsText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString privacyPolicyText = new SpannableString(getText(R.string.title_privacy_policy));
        privacyPolicyText.setSpan(new URLSpan(""), 0, privacyPolicyText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        privacyPolicyText.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(this, android.R.color.white)), 0,
                privacyPolicyText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        CharSequence infoText = TextUtils.concat(termsText, getText(R.string.title_plus), privacyPolicyText);
        return new SpannableString(infoText);
    }
}
