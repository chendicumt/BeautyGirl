package com.panda.beautygirl.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.panda.beautygirl.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by PC on 2017/10/16.
 */

public class GirlSplash extends AppCompatActivity {

    @BindView(R.id.splash_text)
    TextView textView;

    public Timer timer=new Timer();
    private int count=3;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        timer.schedule(timerTask,0,1000);
    }

    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what)
            {
                case 1:
                    if(count>0)
                    {
                        textView.setText(" "+count+" 秒后跳转 ");
                        count--;
                    }
                    else{
                        timer.cancel();
                        Intent intent=new Intent(GirlSplash.this,BeautyGirlActivity.class);
                        startActivity(intent);
                    }
                    break;
                default:
                    break;
            }
        }
    };
    TimerTask timerTask=new TimerTask() {
        @Override
        public void run() {
            Message message=new Message();
            message.what=1;
            handler.sendMessage(message);
        }
    };


    public void splashTextListener(View view)
    {
        timer.cancel();
        Intent intent=new Intent(GirlSplash.this,BeautyGirlActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }
}
