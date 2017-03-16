package com.its.its.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.its.R;
import com.its.its.model.tasks.LoginTask;
import com.its.its.presenter.security.EncryptDecrypt;

/**
 * Created by Chinh Bui on 3/6/2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText editUserName_Login, editPassword_Login;
    private Button btnPlay_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        addEvents();
    }

    private void addEvents() {
        btnPlay_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask(LoginActivity.this)
                        .execute(
                                "http://192.168.100.14:8080/Demo/users/login"
                                , EncryptDecrypt.encrypt(editUserName_Login.getText().toString(), getResources().getString(R.string.khoa))
                                , EncryptDecrypt.encrypt(editPassword_Login.getText().toString(), getResources().getString(R.string.khoa))
                        );
            }
        });
    }

    private void initView() {
        editUserName_Login = (EditText) findViewById(R.id.editUserName_Login);
        editPassword_Login = (EditText) findViewById(R.id.editPassword_Login);
        btnPlay_Login = (Button) findViewById(R.id.btnPlay_Login);
    }
}
