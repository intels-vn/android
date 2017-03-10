package com.its.its.view;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.its.its.R;
import com.its.its.model.tasks.RegisterTask;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername_Register, edtPassword_Register, edtRetypePass_Register, edtPhone_Register, edtEmail_Register;
    Button btnJoin_Register;
    private String username_reg, password_reg, retypePass_reg, phone_reg, email_reg;
    private static String register_url = "http://192.168.100.7:8080/Demo/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        addEvents();
    }

    private void initView() {
        edtUsername_Register = (EditText) findViewById(R.id.edtUsername_Register);
        edtPassword_Register = (EditText) findViewById(R.id.edtPassword_Register);
        edtRetypePass_Register = (EditText) findViewById(R.id.edtRetypePass_Register);
        edtPhone_Register = (EditText) findViewById(R.id.edtPhone_Register);
        edtEmail_Register = (EditText) findViewById(R.id.edtEmail_Register);
        btnJoin_Register = (Button) findViewById(R.id.btnJoin_Register);
    }

    private void addEvents() {
        btnJoin_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_reg = edtUsername_Register.getText().toString();
                password_reg = edtPassword_Register.getText().toString();
                retypePass_reg = edtRetypePass_Register.getText().toString();
                phone_reg = edtPhone_Register.getText().toString();
                email_reg = edtEmail_Register.getText().toString();

                if(validation()){
                    RegisterTask registerTask = new RegisterTask();
                }else{
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.validate_empty_form), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validation(){
        boolean valid = true;

        if(username_reg.isEmpty()){
            edtUsername_Register.setError(getResources().getString(R.string.validate_username));
            valid = false;
        }
        if(password_reg.isEmpty()){
            edtPassword_Register.setError(getResources().getString(R.string.validate_password));
            valid = false;
        }
        if(retypePass_reg.isEmpty()){
            edtRetypePass_Register.setError(getResources().getString(R.string.validate_retype_password));
            valid = false;
        }
        if(phone_reg.isEmpty()){
            edtPhone_Register.setError(getResources().getString(R.string.validate_phone));
            valid = false;
        }
        if(!Patterns.PHONE.matcher(phone_reg).matches()){
            edtPhone_Register.setError(getResources().getString(R.string.validate_invalid_phone));
            valid = false;
        }
        if(email_reg.isEmpty()){
            edtEmail_Register.setError(getResources().getString(R.string.validate_email));
            valid = false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email_reg).matches()){
            edtEmail_Register.setError(getResources().getString(R.string.validate_invalid_email));
            valid = false;
        }
        return valid;
    }
}
