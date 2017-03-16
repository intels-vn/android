package com.its.its.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.its.its.R;
import com.its.its.model.tasks.ChangePasswordTask;
import com.its.its.model.tasks.UpdateProfileTask;
import com.its.its.presenter.security.EncryptDecrypt;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edtOldPass, edtNewPass, edtRetypePass_ChangePass;
    Button btnApplyChange;
    private String oldPass, newPass, retypePass_ChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initView();
        addEvents();
    }

    private void initView() {
        edtOldPass = (EditText) findViewById(R.id.edtOldPass);
        edtNewPass = (EditText) findViewById(R.id.edtNewPass);
        edtRetypePass_ChangePass = (EditText) findViewById(R.id.edtRetypePass_ChangePass);
        btnApplyChange = (Button) findViewById(R.id.btnApplyChange);
    }

    private void addEvents() {
        btnApplyChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChangePasswordTask(ChangePasswordActivity.this).execute(
                        "http://192.168.100.14:8080/Demo/user/" +
                                getIntent().getStringExtra("ID") + "/password?password=" +
                                EncryptDecrypt.encrypt(edtNewPass.getText().toString(), getResources().getString(R.string.khoa)) +
                                "&oldpassword=" +
                                EncryptDecrypt.encrypt(edtOldPass.getText().toString(), getResources().getString(R.string.khoa)),
                        getIntent().getStringExtra("TOKEN")
                );
            }
        });
    }
}
