package com.fundamentals.academy.ori.threadsgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadTaskActivity extends AppCompatActivity {
    private Button create_bt;
    private Button start_bt;
    private Button cancel_bt;
    private TextView progress_tv;
    private CountThread count_task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_creator_layout);

        create_bt = findViewById(R.id.create_bt);
        start_bt = findViewById(R.id.start_bt);
        cancel_bt = findViewById(R.id.cancel_bt);
        progress_tv = findViewById(R.id.progress_tv);


        create_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count_task = new CountThread(progress_tv);
            }
        });

        start_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != count_task) {
                    count_task.execute();
                }
            }
        });

        cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != count_task) {
                    count_task.cancel();
                    count_task = null;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != count_task) {
            count_task.cancel();
            count_task = null;
        }
    }
}
