package com.its.its.model.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.entity.RegisterAndLogin;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class RegisterTask extends AsyncTask<String, Void, String>{
    private Activity activity;

    public RegisterTask(Activity activity) {
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
        String username = params[1];
        String password = params[2];
        String phonenumber = params[3];
        String email = params[4];
        String fullname = params[5];
        String deviceId = params[6];

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json;charset=UTF-8");
            headers.put("Content-Type", "application/json");
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("DeviceId", deviceId);

            HashMap<String, String> bodies = new HashMap<String, String>();
            bodies.put("username", username);
            bodies.put("password", password);
            bodies.put("phonenumber", phonenumber);
            bodies.put("fullname", fullname);
            bodies.put("email", email);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.POST, api, headers, bodies);

            DataReturn dataReturn = new Gson().fromJson(inputStreamReader, DataReturn.class);
            String status = dataReturn.getStatus();
            Log.e("Status ", status);

            switch (status){
                case "200":
                    String data = dataReturn.getData().toString();
                    RegisterAndLogin registerAndLogin = new Gson().fromJson(data, RegisterAndLogin.class);

                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.putExtra("TOKEN", registerAndLogin.getToken());
                    intent.putExtra("ID", registerAndLogin.getId());
                    activity.startActivity(intent);
                    break;
                case "400":
                    break;
                case "500":
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error  ", e.getMessage());
        }

        return result;
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
