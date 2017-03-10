package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.http.CommonRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class RegisterTask extends AsyncTask<String, Void, String>{
    Activity register;

    public RegisterTask(Activity register) {
        this.register = register;
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
        String phone = params[3];
        String email = params[4];

        String deviceId = Settings.Secure.getString(register.getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("ANDROID ID   ", deviceId);

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json;charset=UTF-8");
            headers.put("Localization", register.getResources().getString(R.string.localization));
            headers.put("DeviceId", deviceId);

            HashMap<String, String> bodies = new HashMap<String, String >();
            bodies.put("useraccount", username);
            bodies.put("password", password);
            bodies.put("phonenumber", phone);
            bodies.put("emai;", email);

            InputStreamReader reader = CommonRequest.receiveResponse(CommonRequest.POST, api, headers, bodies);

            DataReturn dataReturn = new Gson().fromJson(reader, DataReturn.class);
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
