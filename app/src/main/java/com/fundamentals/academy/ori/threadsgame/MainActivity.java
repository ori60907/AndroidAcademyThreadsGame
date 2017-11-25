package com.fundamentals.academy.ori.threadsgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAsyncTaskActivityButtonClick(View view){
        Intent new_activity_intent = new Intent(this, async_task_activity.class);
        startActivity(new_activity_intent);
    }

    public void onThreadActivityButtonClick(View view){
        Intent new_activity_intent = new Intent(this, ThreadTaskActivity.class);
        startActivity(new_activity_intent);
    }
}
