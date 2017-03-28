package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.User;
import com.its.its.model.http.CommonRequest;
import com.its.its.presenter.security.EncryptDecrypt;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/19/2017.
 */

public class GetUserProfileTask extends AsyncTask<String, Void, DataReturn> {
    Activity activity;
    EditText edtFullName, edtEmail, edtPhoneNumber;

    public GetUserProfileTask(Activity activity, EditText edtFullName, EditText edtEmail, EditText edtPhoneNumber) {
        this.activity = activity;
        this.edtFullName = edtFullName;
        this.edtEmail = edtEmail;
        this.edtPhoneNumber = edtPhoneNumber;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();

        String api = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);
            headers.put("Accept", "application/json");

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.GET, api, headers, null);
            if(inputStreamReader != null){
                result = new Gson().fromJson(inputStreamReader, DataReturn.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(DataReturn s) {
        super.onPostExecute(s);
        if(s != null){
            switch (s.getStatus()){
                case "200":
                    String data = s.getData().toString();
                    User user = new Gson().fromJson(data, User.class);
                    edtFullName.setText(EncryptDecrypt.decrypt(user.getFullname(), activity.getResources().getString(R.string.khoa)));
                    edtPhoneNumber.setText(EncryptDecrypt.decrypt(user.getPhonenumber(), activity.getResources().getString(R.string.khoa)));
                    edtEmail.setText(EncryptDecrypt.decrypt(user.getEmail(), activity.getResources().getString(R.string.khoa)));
                    break;
                default:
                    break;
            }
        }
    }
}
