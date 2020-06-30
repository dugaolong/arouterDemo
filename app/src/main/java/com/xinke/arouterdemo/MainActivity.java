package com.xinke.arouterdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private int index = 0;
    NotificationManager notificationManager;
    NotificationCompat.Builder builder;


    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        createNotify();

        findViewById(R.id.but_onClick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.简单的跳转
                //这里的path就是要跳转到界面去的注解地址
//                ARouter.getInstance().build("/test/testActivity").navigation();

                //2. 一般界面跳转都要携带参数
                ARouter.getInstance().build("/test/testActivity")
                        .withString("userName", "张三")
                        .withInt("age", 123)
                        .navigation();
            }
        });


        findViewById(R.id.notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.post(runnable);
            }
        });

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            index++;
            builder.setContentText("领导：你几点下班？" + index);
            notificationManager.notify(1, builder.build());
            handler.postDelayed(runnable, 3000);
            Log.v("runnable", Thread.currentThread().getName());
            if(index==5){
                handler.removeCallbacks(runnable);
            }

        }
    };

    public void createNotify(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            notificationManager = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);

            //创建自定义的渠道
            String channelid = "chat1";
            String channelName = "聊天消息";
            NotificationChannel channel = new NotificationChannel(channelid, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(mContext, channelid);

            channel.enableLights(true); //是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.BLACK); //小红点颜色
            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            notificationManager.createNotificationChannel(channel);

            builder.setOngoing(true);
            //同时，Notification.Builder需要多设置一个
            builder.setChannelId(channelid);
            builder.setColor(Color.RED);
            builder.setWhen(System.currentTimeMillis());
            builder.setContentTitle("QQ消息");
            builder.setContentText("领导：你几点下班？" + index);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large_ic_launcher));


        }
    }
}
