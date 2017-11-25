package com.fundamentals.academy.ori.threadsgame;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by User on 25/11/2017.
 */

public class CountTask extends AsyncTask<Void, Integer, Void> {
    private TextView progress_tv;

    public CountTask(TextView progress_tv_param) {
        progress_tv = progress_tv_param;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int i = 0;
        for(i=1; i<=10; ++i){
            SystemClock.sleep(500);
            publishProgress(i);
            if (isCancelled())
                break;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progress_tv.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onCancelled() {
        progress_tv = null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (null != progress_tv){
            progress_tv.setText("DONE!");
        }
    }
}
