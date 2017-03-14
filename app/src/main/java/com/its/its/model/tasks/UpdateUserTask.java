package com.its.its.model.tasks;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by BiLac on 3/10/2017.
 */

public class UpdateUserTask extends AsyncTask<String, Void, String>{
    Activity activity;

    public UpdateUserTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

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
