package com.its.its.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.its.R;
import com.its.its.model.http.CommonRequest;
import com.its.its.model.tasks.UpdateProfileTask;
import com.its.its.presenter.interactor.UpdateProfileInterface;
import com.its.its.presenter.security.EncryptDecrypt;

public class UpdateProfileActivity extends AppCompatActivity implements UpdateProfileInterface{
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

    //Fetch the ID from the update profile layout.
    private void initView() {
        edtFullname_Update = (EditText) findViewById(R.id.edtFullname_Update);
        edtEmail_Update = (EditText) findViewById(R.id.edtEmail_Update);
        edtPhone_Update = (EditText) findViewById(R.id.edtPhone_Update);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
    }

    //Add events to the update button.
    private void addEvents() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateProfile();
            }
        });
    }

    //Validate the update profile form.
    private boolean validation() {
        boolean valid = true;

        if(fullname_update.isEmpty()){
            edtFullname_Update.setError(getResources().getString(R.string.validate_fullname));
            valid = false;
        }

        if(email_update.isEmpty()){
            edtEmail_Update.setError(getResources().getString(R.string.validate_email));
            valid = false;
        }

        if(phone_update.isEmpty()){
            edtPhone_Update.setError(getResources().getString(R.string.validate_phone));
            valid = false;
        }

        return valid;
    }

    @Override
    public void UpdateProfile() {
        fullname_update = edtFullname_Update.getText().toString();
        email_update = edtEmail_Update.getText().toString();
        phone_update = edtPhone_Update.getText().toString();

        if(validation()) {
            new UpdateProfileTask(UpdateProfileActivity.this).execute(
                    CommonRequest.link + "user/" + getIntent().getStringExtra("ID") +
                            "/profile?email=" + EncryptDecrypt.encrypt(email_update, getResources().getString(R.string.khoa))+
                            "&phone=" + EncryptDecrypt.encrypt(phone_update, getResources().getString(R.string.khoa)) +
                            "&fullname=" + EncryptDecrypt.encrypt(fullname_update, getResources().getString(R.string.khoa)),
                    getIntent().getStringExtra("TOKEN")
            );
        }else{
            Toast.makeText(UpdateProfileActivity.this, getResources().getString(R.string.validate_empty_form), Toast.LENGTH_SHORT).show();
        }
    }
}
