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
import com.its.its.view.RegisterActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class RegisterTask extends AsyncTask<String, Void, DataReturn>{
    private Activity activity;

    public RegisterTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();
        
        String api = params[0];
        String username = params[1];
        String password = params[2];
        String phonenumber = params[3];
        String email = params[4];
        String fullname = params[5];
        String deviceId = params[6];

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Accept", "application/json");
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
            if(inputStreamReader != null){
                result = new Gson().fromJson(inputStreamReader, DataReturn.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error  ", e.getMessage());
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
                    RegisterAndLogin registerAndLogin = new Gson().fromJson(data, RegisterAndLogin.class);
                    
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.putExtra("ID", registerAndLogin.getId());
                    intent.putExtra("TOKEN", registerAndLogin.getToken());
                    activity.startActivity(intent);

                    Toast.makeText(activity, s.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case "400":
                    Toast.makeText(activity, s.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case "500":
                    Toast.makeText(activity, s.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }else{
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
