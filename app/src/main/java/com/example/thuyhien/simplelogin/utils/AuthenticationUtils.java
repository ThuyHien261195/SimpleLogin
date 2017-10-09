package com.example.thuyhien.simplelogin.utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by thuyhien on 10/5/17.
 */

public class AuthenticationUtils {

    public static boolean checkNotEmptyInput(String inputText) {
        return !TextUtils.isEmpty(inputText);
    }

    public static boolean checkValidEmail(String inputText) {
        return Patterns.EMAIL_ADDRESS.matcher(inputText).matches();
    }

    public static boolean checkValidPass(String inputText) {
        return (inputText != null && inputText.length() >= 6);
    }
}
