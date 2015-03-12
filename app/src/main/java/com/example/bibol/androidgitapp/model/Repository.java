package com.example.bibol.androidgitapp.model;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;


public class Repository implements Serializable {
    private String name, fullName;
    private Integer id;

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public Integer getWatchersCount() {
        return watchersCount;
    }

    public Integer getForksCount() {
        return forksCount;
    }

    private Integer stargazersCount,watchersCount,forksCount;
    private String description;

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

    public static Repository fromJSON(JSONObject json) throws InstantiationException {
        Repository repository = new Repository();
        try {
            repository.name = json.getString("name");
            repository.fullName = json.getString("full_name");
            repository.description = json.getString("description");
            repository.id = json.getInt("id");

        } catch (JSONException e) {
            e.printStackTrace();
            throw new InstantiationException("Can't parse User from given JSON");
        }
        return repository;
    }

}