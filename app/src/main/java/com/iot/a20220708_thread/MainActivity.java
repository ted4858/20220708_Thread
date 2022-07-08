package com.iot.a20220708_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Thread thread;
    private TextView textView;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

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
    }
}