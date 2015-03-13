package com.example.bibol.androidgitapp.model;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class Repository {
    private String name, fullName;
    private int id;
    private String description;
    private int stargazersCount,watchersCount,forksCount;

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Repository fromJSON(JSONObject json) throws InstantiationException {
        Repository repository = new Repository();
        try {
            repository.name = json.getString("name");
            repository.fullName = json.getString("full_name");
            repository.description = json.getString("description");
            repository.id = json.getInt("id");
            repository.stargazersCount = json.getInt("stargazers_count");
            repository.watchersCount = json.getInt("watchers_count");
            repository.forksCount = json.getInt("forks_count");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new InstantiationException("Can't parse Repository from given JSON");
        }
        return repository;
    }
}
