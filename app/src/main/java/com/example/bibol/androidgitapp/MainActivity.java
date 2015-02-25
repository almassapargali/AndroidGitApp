package com.example.bibol.androidgitapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibol.androidgitapp.model.User;


public class MainActivity extends Activity {

    EditText usernameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(oclBtnOk);

        usernameField = (EditText) findViewById(R.id.editText);
    }

    View.OnClickListener oclBtnOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = usernameField.getText().toString(); // bibol

            final ProgressDialog dialog = ProgressDialog.show(MainActivity.this,
                    "Getting user information ...",
                    null, false, false);

            GithubApiClient.instance().getUser(username, new GithubApiClientResponseHandler<User>() {
                @Override
                public void onSuccess(User user) {
                    // show user activity

                   // public void onClick(View v){
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        startActivity(intent);
                   // }

                    dialog.dismiss();
                }

                @Override
                public void onFailure(int errorCode, String errorMessage) {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
