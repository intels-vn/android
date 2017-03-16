package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Chinh Bui on 3/16/2017.
 */

public class WithdrawTask extends AsyncTask<String, Void, Void> {

    Activity activity;

    public WithdrawTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(String... params) {
        String api = params[0];
        String token = params[1];

        try {
            Log.d("API ", api);
            Log.d("TOKEN ", token);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
