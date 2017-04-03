package com.its.its.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.its.R;
import com.its.its.model.http.CommonRequest;
import com.its.its.model.tasks.LoginTask;
import com.its.its.presenter.interactor.LoginInterface;
import com.its.its.presenter.security.EncryptDecrypt;

/**
 * Created by Chinh Bui on 3/6/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginInterface{

    private EditText editUserName_Login, editPassword_Login;
    private Button btnPlay_Login;
    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        addEvents();
    }

    //Fetch the ID from the login layout.
    private void initView() {
        editUserName_Login = (EditText) findViewById(R.id.editUserName_Login);
        editPassword_Login = (EditText) findViewById(R.id.editPassword_Login);
        btnPlay_Login = (Button) findViewById(R.id.btnPlay_Login);
    }

    //Add events to login button.
    private void addEvents() {
        btnPlay_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    //Validate the login form.
    private boolean validation() {
        boolean valid = true;

        if(username.isEmpty()){
            editUserName_Login.setError(getResources().getString(R.string.validate_username));
            valid = false;
        }

        if(password.isEmpty()){
            editPassword_Login.setError(getResources().getString(R.string.validate_password));
            valid = false;
        }
        return valid;
    }

    @Override
    public void Login() {
        username = editUserName_Login.getText().toString();
        password = editPassword_Login.getText().toString();

        if(validation()) {
            new LoginTask(LoginActivity.this)
                    .execute(
                            CommonRequest.link + "users/login",
                            EncryptDecrypt.encrypt(username, getResources().getString(R.string.khoa)),
                            EncryptDecrypt.encrypt(password, getResources().getString(R.string.khoa))
                    );
        }
    }
}
