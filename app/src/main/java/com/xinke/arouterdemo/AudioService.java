package com.xinke.arouterdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AudioService extends Service {

    public static final String CHANNEL_ID = "com.xinke.arouterdemo.AudioService";
    public static final String CHANNEL_NAME = "播放音乐";

    NotificationManager notificationManager;
    NotificationCompat.Builder builder;

    @Override
    public IBinder onBind(Intent intent) {

        Log.e("AudioService", "onBind");
        return null;
    }


    @Override
    public void onCreate() {

        Log.e("AudioService", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("AudioService", "onStartCommand");
        createNotify();

//        registerNotificationChannel();
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        startForeground(1, builder.build());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    create2();
                    notificationManager.notify(2, builder.build());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



//                notificationManager.notify(1, builder.build());


//                Intent intent = new Intent(MyApplication.getApp(),BackActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//-----重要
//
//                startActivity(intent);

            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("AudioService", "onDestroy");
        super.onDestroy();
    }

//    /**
//     * 注册通知通道
//     */
//    private void registerNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel notificationChannel = mNotificationManager.getNotificationChannel(CHANNEL_ID);
//            if (notificationChannel == null) {
//                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
//                //是否在桌面icon右上角展示小红点
//                channel.enableLights(true);
//                //小红点颜色
//                channel.setLightColor(Color.RED);
//                //通知显示
//                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//                //是否在久按桌面图标时显示此渠道的通知
//                //channel.setShowBadge(true);
//                mNotificationManager.createNotificationChannel(channel);
//                mNotificationManager.getNotificationChannel(CHANNEL_ID);
//            }
//        }
//    }

    public void createNotify() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            notificationManager = (NotificationManager) MyApplication.getApp().getSystemService(NOTIFICATION_SERVICE);

            //创建自定义的渠道
            String channelid = "chat1";
            String channelName = "聊天消息";
            NotificationChannel channel = new NotificationChannel(channelid, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);

            builder = new NotificationCompat.Builder( MyApplication.getApp(), channelid);

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
            builder.setContentText("你几点下班？");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large_ic_launcher));


        }
    }

    public void create1(){
        Intent fullScreenIntent = new Intent(this, BackActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
    }
    public void create2(){
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setCategory(NotificationCompat.CATEGORY_CALL);

        builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        Intent fullScreenIntent = new Intent(this, BackActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setFullScreenIntent(pendingIntent, true);
    }
}
