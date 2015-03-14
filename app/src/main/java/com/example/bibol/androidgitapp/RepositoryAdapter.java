package com.example.bibol.androidgitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bibol.androidgitapp.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends ArrayAdapter<Repository> {

    private Context mContext;

    public RepositoryAdapter(Context context, List<Repository> repos) {
        super(context, R.layout.repositories_list, repos);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.repositories_list, null);
        }

        Repository repo = getItem(position);
        ((TextView) convertView.findViewById(R.id.repo_name)).setText(repo.getFullName());
        ((TextView) convertView.findViewById(R.id.description)).setText(repo.getDescription());
        return convertView;
    }
}
