package com.example.bibol.androidgitapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.bibol.androidgitapp.model.BitmapLruCache;
import com.example.bibol.androidgitapp.model.Repository;
import com.example.bibol.androidgitapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends Activity {

    User currentUser;

    List<Repository> repositories;
    ArrayAdapter<Repository> adapter;

    ListView reposView;

    TextView emailView,usernameView;
    TextView followersCountView,followingCountView,reposCountView;
    private NetworkImageView networkImageView;

//    TextView repoNameView,descriptionView;

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setViews();

        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        imageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(
                BitmapLruCache.getDefaultLruCacheSize()));

        currentUser = (User) getIntent().getSerializableExtra(MainActivity.getIntentUserExtra());
        setUserDeatils();
        loadUserRepositories();
    }

    private void setViews() {
        networkImageView = (NetworkImageView) findViewById(R.id.avatar);
        emailView = (TextView) findViewById(R.id.email_name);
        usernameView = (TextView) findViewById(R.id.user_name);
        followersCountView = (TextView) findViewById(R.id.followersCountView);
        followingCountView = (TextView) findViewById(R.id.followingCountView);
        reposCountView = (TextView) findViewById(R.id.reposCountView);
    }

    private void setUserDeatils() {
        emailView.setText(currentUser.getEmail());
        usernameView.setText(currentUser.getUsername());
        followersCountView.setText(currentUser.getFollowersCount().toString());
        followingCountView.setText(currentUser.getFollowingCount().toString());
        reposCountView.setText(currentUser.getReposCount().toString());
        networkImageView.setImageUrl(currentUser.getAvatarUrl(), imageLoader);
    }

    private void loadUserRepositories() {
        GithubApiClient.instance().getUserRepos(currentUser.getUsername(), new GithubApiClientResponseHandler<List<Repository>>() {
            @Override
            public void onSuccess(List<Repository> repositoryList) {
                repositories = repositoryList;

                adapter = new ArrayAdapter<Repository>(UserActivity.this,R.layout.single_list,R.id.repo_name,repositories);

                reposView = (ListView) findViewById(R.id.reposList);
                reposView.setAdapter(adapter);

            }

            @Override
            public void onFailure(int errorCode, String errorMessage) {
                Toast.makeText(UserActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}