package com.fundamentals.academy.ori.threadsgame;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.logging.LoggingPermission;

/**
 * Created by User on 25/11/2017.
 */

public class CountThread extends Thread {
    private TextView progress_tv;
    private Handler mainUIHandler;
    private boolean is_cancelled;

    public CountThread(TextView progress_tv_param) {
        progress_tv = progress_tv_param;
        is_cancelled = false;

        /* Create a handler to run on the main ui */
        mainUIHandler = new Handler(Looper.getMainLooper());
    }



    /**************** Interface of AsyncTask ****************/

    protected void onPreExecute(){
        /* do nothing */
        return;
    }

    protected void doInBackground(){
        int i = 0;
        for(i=1; i<=10; ++i){
            SystemClock.sleep(500);
            publishProgress(i);
            if (isCancelled())
                break;
        }
    }

    protected void onCancelled(){
        progress_tv = null;
    }

    protected void onProgressUpdate(Integer... values) {
        progress_tv.setText(String.valueOf(values[0]));
    }

    protected void onPostExecute() {
        if (null != progress_tv){
            progress_tv.setText("DONE!");
        }
    }

    /**************** BACKGROUND code ****************/

    @Override
    public void run() {
        callOnPreExecute();
        doInBackground();
        if (!isCancelled()){
            finishExecute();
        }
        else{
            cancelExecution();
        }
    }

    public void execute(){
        this.start();
    }

    protected void callOnPreExecute(){
        mainUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onPreExecute();
            }
        });
    }

    protected void cancelExecution(){
        mainUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onCancelled();
            }
        });
    }

    protected boolean isCancelled(){
        return is_cancelled;
    }


    public void cancel(){
        is_cancelled = true;
    }

    protected void publishProgress(final Integer progress){
        mainUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onProgressUpdate(progress);
            }
        });
    }


    protected void finishExecute(){
        mainUIHandler.post(new Runnable() {
            @Override
            public void run() {
                onPostExecute();
            }
        });
    }

}
