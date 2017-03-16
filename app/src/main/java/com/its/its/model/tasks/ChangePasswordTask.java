package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by BiLac on 3/16/2017.
 */

public class ChangePasswordTask extends AsyncTask<String, Void, String> {
    Activity activity;

    public ChangePasswordTask(Activity activity) {
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
        String oldPass = params[1];
        String newPass = params[2];


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
