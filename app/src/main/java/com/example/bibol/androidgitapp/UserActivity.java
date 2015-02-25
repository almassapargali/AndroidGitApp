package com.example.bibol.androidgitapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.bibol.androidgitapp.model.User;


public class UserActivity extends Activity {

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra(MainActivity.INTENT_USER_EXTRA);
    }



}
