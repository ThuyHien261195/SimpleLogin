package com.example.thuyhien.simplelogin.utils;

import com.example.thuyhien.simplelogin.data.network.exception.AuthenticationException;
import com.example.thuyhien.simplelogin.data.network.exception.FacebookAuthenticationException;
import com.example.thuyhien.simplelogin.data.network.exception.LoadDataException;
import com.example.thuyhien.simplelogin.data.network.exception.LoadProfileException;
import com.example.thuyhien.simplelogin.data.network.exception.UnKnowException;

import org.json.JSONObject;

import okhttp3.ResponseBody;

/**
 * Created by thuyhien on 10/5/17.
 */

public class RetrofitUtils {
    public static final String AUTH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwcm9qZWN0X2lkIjoiNTg2NWU1YTdmYmE5NWU4MmE4ODA3MmJkIiwiaWF0IjoxNDY5NzkxMDc4fQ.Sv1PG7ZFooqSufUyGQHEbYRqYSVMRIANkV36HVBjVvQ";

    public static Exception createAuthenException(ResponseBody responseBody) {
        if (responseBody != null) {
            try {
                JSONObject jsonError = new JSONObject(responseBody.string());
                return new AuthenticationException(jsonError.getString("message"));
            } catch (Exception e) {
                return e;
            }
        }
        return new UnKnowException();
    }

    public static Exception createLoadDataException(ResponseBody responseBody) {
        if (responseBody != null) {
            try {
                JSONObject jsonError = new JSONObject(responseBody.string());
                if (jsonError.has("message")) {
                    return new LoadDataException(jsonError.getString("message"));
                }

                if (jsonError.has("error")) {
                    return new LoadDataException(jsonError.getString("error"));
                }
            } catch (Exception e) {
                return e;
            }
        }
        return new UnKnowException();
    }

    public static Exception createFacebookAuthenException(ResponseBody responseBody) {
        if (responseBody != null) {
            try {
                JSONObject jsonError = new JSONObject(responseBody.string());
                return new FacebookAuthenticationException(jsonError.getString("message"));
            } catch (Exception e) {
                return e;
            }
        }
        return new UnKnowException();
    }

    public static Exception createLoadProfileException(ResponseBody responseBody) {
        if (responseBody != null) {
            try {
                JSONObject jsonError = new JSONObject(responseBody.string());
                return new LoadProfileException(jsonError.getString("message"));
            } catch (Exception e) {
                return e;
            }
        }
        return new UnKnowException();
    }
}
