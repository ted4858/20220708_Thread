package com.iot.a20220708_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private TextView textView;
    private int number = 0;

    private long count = 10000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ((TextView)findViewById(R.id.textView2)).setText(count+"");
            count -= 1;

            if (count == 0) {
                count = 10000;
            }else {
                handler.postDelayed(runnable, 1000);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView1);

        thread = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    number++;
                    Log.i("test","thread running..."+number);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(String.valueOf(number));
                        }
                    });
                }
            }
        };
        thread.start();
        handler.post(runnable);
    }
}