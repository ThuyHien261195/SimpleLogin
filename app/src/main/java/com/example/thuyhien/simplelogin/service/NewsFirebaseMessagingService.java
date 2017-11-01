package com.example.thuyhien.simplelogin.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.ui.activity.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by thuyhien on 10/30/17.
 */

public class NewsFirebaseMessagingService extends FirebaseMessagingService {

    private static final String fox_channel = "fox_channel";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String message = remoteMessage.getNotification().getBody();
            showNotification(message);
        }
    }

    @Override
    public void handleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("gcm.notification.body");
        if (message != null && !message.equals("")) {
            showNotification(message);
        }
    }

    public void showNotification(String message) {
        int notificationId = 0;
        Intent mainIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, fox_channel)
                .setSmallIcon(R.mipmap.ic_fox_rgb_logo)
                .setContentTitle(getString(R.string.fox_notification_title))
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, builder.build());
    }
}
