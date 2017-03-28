package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.http.CommonRequest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Chinh Bui on 3/16/2017.
 */

public class ChangePasswordTask extends AsyncTask<String, Void, DataReturn> {
    Activity activity;

    public ChangePasswordTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();

        String api = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<>();
            headers.put("Accept", "*/*");
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.PUT, api, headers, null);
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

        String status = s.getStatus();
        switch (status){
            case "200":
                Toast.makeText(activity, activity.getResources().getString(R.string.change_password_success), Toast.LENGTH_SHORT).show();
//                Log.d("API: ", api);
                Log.d("Data: ", s.getMessage());
                break;

            default:
                Toast.makeText(activity, activity.getResources().getString(R.string.change_password_failed), Toast.LENGTH_SHORT).show();
//                Log.e("API: ", api);
                Log.e("Error: ", s.getMessage());
                break;
        }
    }
}
