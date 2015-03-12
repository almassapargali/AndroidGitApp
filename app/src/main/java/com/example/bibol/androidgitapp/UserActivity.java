package com.example.bibol.androidgitapp;

import android.app.Activity;
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
    TextView emailView;
    TextView usernameView;
    private NetworkImageView networkImageView;

    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        networkImageView = (NetworkImageView) findViewById(R.id.avatar);
        emailView = (TextView) findViewById(R.id.email_name);
        usernameView = (TextView) findViewById(R.id.user_name);


        currentUser = (User) getIntent().getSerializableExtra(MainActivity.INTENT_USER_EXTRA);

        emailView.setText(currentUser.getEmail());
        usernameView.setText(currentUser.getUsername());


        mRequestQueue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(
                BitmapLruCache.getDefaultLruCacheSize()));
        networkImageView.setImageUrl(currentUser.getAvatarUrl(), imageLoader);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}