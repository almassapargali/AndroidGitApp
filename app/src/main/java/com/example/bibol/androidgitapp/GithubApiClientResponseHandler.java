package com.example.bibol.androidgitapp;

import android.util.Log;

public class GithubApiClientResponseHandler<T> {
    private static final String LOG_TAG = "Github API Response";
    public static final Integer JSON_PARSING_FAILED_ERROR = 0;

    public void onSuccess(T response) {
        Log.e(LOG_TAG, "onSuccess(T) was not overriden, but callback was received");
    }

    public void onFailure(int errorCode, String errorMessage) {
        Log.e(LOG_TAG, "onFailure(int, String) was not overriden, but callback was received");
    }
}
