package com.its.its.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.its.R;
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

    //Fetch the ID from the change password layout.
    private void initView() {
        edtOldPass = (EditText) findViewById(R.id.edtOldPass);
        edtNewPass = (EditText) findViewById(R.id.edtNewPass);
        edtRetypePass_ChangePass = (EditText) findViewById(R.id.edtRetypePass_ChangePass);
        btnApplyChange = (Button) findViewById(R.id.btnApplyChange);
    }

    //Add events to apply change button.
    private void addEvents() {
        btnApplyChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass = edtOldPass.getText().toString();
                newPass = edtNewPass.getText().toString();
                retypePass_ChangePass = edtRetypePass_ChangePass.getText().toString();

                if(validate()) {
                    new UpdateProfileTask(ChangePasswordActivity.this).execute(
                            "http://192.168.100.14:8080/Demo",
                            EncryptDecrypt.encrypt(oldPass, getResources().getString(R.string.khoa)),
                            EncryptDecrypt.encrypt(newPass, getResources().getString(R.string.khoa))
                    );
                }else{
                    Toast.makeText(ChangePasswordActivity.this, getResources().getString(R.string.validate_empty_form), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Validate the change password form.
    private boolean validate() {
        boolean valid = true;

        if(oldPass.isEmpty()){
            edtOldPass.setError(getResources().getString(R.string.validate_old_password));
            valid = false;
        }

        if(newPass.isEmpty()){
            edtNewPass.setError(getResources().getString(R.string.validate_new_password));
            valid = false;
        }

        if(retypePass_ChangePass.isEmpty()){
            edtRetypePass_ChangePass.setError(getResources().getString(R.string.validate_retype_new_password));
            valid = false;
        }

        if(newPass.equals(oldPass)){
            edtNewPass.setError(getResources().getString(R.string.validate_matched_old_password));
            valid = false;
        }

        if(retypePass_ChangePass.equals(newPass)){
            edtRetypePass_ChangePass.setError(getResources().getString(R.string.validate_matched_retype_password));
            valid = false;
        }

        return valid;
    }

}
