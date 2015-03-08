package com.example.bibol.androidgitapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bibol.androidgitapp.model.User;


public class UserActivity extends Activity {

    User currentUser;
    ImageView avatar_view;
    TextView email_view,username_view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

    //    avatar_view = (ImageView) findViewById(R.id.main_image);
        email_view = (TextView) findViewById(R.id.email_name);
        username_view = (TextView) findViewById(R.id.user_name);


        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra(MainActivity.INTENT_USER_EXTRA);

        String gUsername = intent.getStringExtra(currentUser.getUsername());
        String gEmail = intent.getStringExtra(currentUser.getEmail());
    //    String gUrl = intent.getStringExtra(currentUser.getAvatarUrl());


        email_view.setText(gEmail);
        username_view.setText(gUsername);
      //  avatar_view.setImageURI(Uri.parse(gUrl));



    }




}
