package com.its.its.model.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.its.its.R;
import com.its.its.model.entity.DataReturn;
import com.its.its.model.http.CommonRequest;
import com.its.its.view.LoginActivity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by BiLac on 3/10/2017.
 */

public class LogoutTask extends AsyncTask<String, Void, DataReturn>{

    Activity activity;

    @Override
    protected void onPostExecute(DataReturn dataReturn) {
        super.onPostExecute(dataReturn);
        if(dataReturn != null){
            switch (dataReturn.getStatus()){
                case "200":
                    Intent intent = new Intent(activity, LoginActivity.class);
                    activity.startActivity(intent);
                    Toast.makeText(activity, "Log out success", Toast.LENGTH_SHORT).show();
                    break;
                case "400":
                    break;
                case "500":
                    break;
                default:
                    break;
            }
        }
    }

    public LogoutTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected DataReturn doInBackground(String... params) {
        DataReturn result = new DataReturn();
        String url = params[0];
        String token = params[1];

        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Localization", activity.getResources().getString(R.string.localization));
            headers.put("Authorization", token);

            InputStreamReader inputStreamReader = CommonRequest.receiveResponse(CommonRequest.DELETE, url, headers, null);
            if(inputStreamReader != null){
                result = new Gson().fromJson(inputStreamReader, DataReturn.class);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error: ", e.getMessage());
        }
        return result;
    }
}
