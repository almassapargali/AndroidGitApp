package com.example.bibol.androidgitapp;

import android.util.Log;

import com.example.bibol.androidgitapp.model.*;
import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Bibol on 24.02.2015.
 */
public class GithubApiClient {
    private static final String LOG_TAG = "Github API";
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

    public void getUser(final String username, final GithubApiClientResponseHandler<User> responseHandler) {
        client.get(getAbsoluteUrl("users/" + username), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d(LOG_TAG, response.toString());
                try {
                    responseHandler.onSuccess(User.fromJSON(response));
                } catch (InstantiationException e) {
                    responseHandler.onFailure(GithubApiClientResponseHandler.JSON_PARSING_FAILED_ERROR, e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e(LOG_TAG, errorResponse.toString());
                try {
                    responseHandler.onFailure(statusCode, errorResponse.getString("message"));
                } catch (JSONException e) {
                    responseHandler.onFailure(GithubApiClientResponseHandler.JSON_PARSING_FAILED_ERROR, errorResponse.toString());
                }
            }
        });
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
