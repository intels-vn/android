package com.its.its.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.its.R;
import com.its.its.model.tasks.UpdateUserTask;
import com.its.its.presenter.security.EncryptDecrypt;

public class UpdateProfileActivity extends AppCompatActivity {
    EditText edtFullname_Update, edtEmail_Update, edtPhone_Update;
    Button btnUpdate;
    private String fullname_update, email_update, phone_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        initView();
        addEvents();
    }

    private void initView() {
        edtFullname_Update = (EditText) findViewById(R.id.edtFullname_Update);
        edtEmail_Update = (EditText) findViewById(R.id.edtEmail_Update);
        edtPhone_Update = (EditText) findViewById(R.id.edtPhone_Update);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
    }

    private void addEvents() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullname_update = edtFullname_Update.getText().toString();
                email_update = edtEmail_Update.getText().toString();
                phone_update = edtPhone_Update.getText().toString();

                new UpdateUserTask(UpdateProfileActivity.this).execute(
//                        "http://"
                        EncryptDecrypt.encrypt(fullname_update, getResources().getString(R.string.khoa)),
                        EncryptDecrypt.encrypt(email_update, getResources().getString(R.string.khoa)),
                        EncryptDecrypt.encrypt(phone_update, getResources().getString(R.string.khoa))
                );
            }
        });
    }
}
