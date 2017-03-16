package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.RegisterAndLogin;
import com.its.its.model.entity.User;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.MainActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class UpdateProfileTask extends AsyncTask<String, Void, String>{
    Activity activity;

    public UpdateProfileTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";

        String api = params[0];
        String id = params[1];
        String fullname = params[2];
        String email = params[3];
        String phone = params[4];
        String oldPass = params[5];
        String newPass = params[6];

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "*/*");
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", " ");

            HashMap<String, String> bodies = new HashMap<String, String>();
            bodies.put("id", id);
            bodies.put("fullname", fullname);
            bodies.put("email", email);
            bodies.put("phone", phone);
            bodies.put("password", newPass);
            bodies.put("oldpassword", oldPass);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.PUT, api, headers, bodies);

            DataReturn dataReturn = new Gson().fromJson(inputStreamReader, DataReturn.class);
            String status = dataReturn.getStatus();

            switch (status){
                case "200":
                    Toast.makeText(activity, "Updated profile successfully", Toast.LENGTH_SHORT).show();
                    break;
                case "400":
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
