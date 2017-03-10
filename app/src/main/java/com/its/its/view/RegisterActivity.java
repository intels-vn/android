package com.its.its.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.its.its.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername_Register, edtPassword_Register, edtRetypePass_Register, edtPhone_Register, edtEmail_Register;
    Button btnJoin_Register;

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

    }
}
