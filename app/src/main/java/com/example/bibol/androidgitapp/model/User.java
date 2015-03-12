package com.example.bibol.androidgitapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private String username, fullName, email;
    private Integer id;
    private String avatarUrl;
    private String companyName, location, website;
    private Integer followersCount, followingCount, reposCount;

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsite() {
        return website;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public Integer getFollowingCount() {
        return followingCount;
    }

    public Integer getReposCount() {
        return reposCount;
    }

    public static User fromJSON(JSONObject json) throws InstantiationException {
        User user = new User();
        try {
            user.username = json.getString("login");
            user.id = json.getInt("id");
            user.fullName = json.getString("name");
            user.email = json.getString("email");
            user.avatarUrl = json.getString("avatar_url");
            user.companyName = json.getString("company");
            user.location = json.getString("location");
            user.website = json.getString("blog");
            user.followersCount = json.getInt("followers");
            user.followingCount = json.getInt("following");
            user.reposCount = json.getInt("public_repos");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new InstantiationException("Can't parse User from given JSON");
        }
        return user;
    }
}
