package com.example.bibol.androidgitapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.bibol.androidgitapp.model.BitmapLruCache;
import com.example.bibol.androidgitapp.model.User;


public class UserActivity extends Activity {

    User currentUser;
    private ImageView avatarView;
    private TextView email_view,username_view;


    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;


    private NetworkImageView networkImageView;

    private static final String IMAGE_URL = "http://developer.android.com/images/gp-device.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarView = (ImageView) findViewById(R.id.avatar);
        email_view = (TextView) findViewById(R.id.email_name);
        username_view = (TextView) findViewById(R.id.user_name);

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra(MainActivity.INTENT_USER_EXTRA);

        String gUsername = intent.getStringExtra(currentUser.getUsername());
        String gEmail = intent.getStringExtra(currentUser.getEmail());
        String gUrl = intent.getStringExtra(currentUser.getAvatarUrl());


        email_view.setText(gEmail);
        username_view.setText(gUsername);


        networkImageView = (NetworkImageView) findViewById(R.id.networkimage);


        mRequestQueue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(
                BitmapLruCache.getDefaultLruCacheSize()));
    }

    public void btnLoadImageClick(View v) {

        imageLoader.get(IMAGE_URL, ImageLoader.getImageListener(avatarView,
                R.drawable.ic_launcher, R.drawable.githubmainicon));
    }

    public void btnLoadNetWorkImageViewClick(View v) {
        networkImageView.setImageUrl(IMAGE_URL, imageLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}