package com.example.bibol.androidgitapp;

import com.loopj.android.http.*;

/**
 * Created by Bibol on 24.02.2015.
 */
public class GithubApiClient {
    private static final String BASE_URL = "https://api.github.com/";

    private AsyncHttpClient client;
    private static GithubApiClient instance = new GithubApiClient();

    public static GithubApiClient instance() {
        return instance;
    }

    private GithubApiClient() {
        if (instance != null) {
            throw new InstantiationError("This is singletone. Use instance() instead.");
        }
        client = new AsyncHttpClient();
        client.setUserAgent("Github Api Client");
    }

    public void getUser(String username, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl("users/" + username), null, responseHandler);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
