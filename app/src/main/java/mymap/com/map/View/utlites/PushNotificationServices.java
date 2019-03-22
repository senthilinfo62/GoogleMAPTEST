package mymap.com.map.View.utlites;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import mymap.com.map.R;
import mymap.com.map.View.activitites.MainActivity;


public class PushNotificationServices extends FirebaseMessagingService {
    public static int NOTIFICATION_MAIN = 100;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (!remoteMessage.getData().isEmpty()) {
            for (String key : remoteMessage.getData().keySet()) {
                String value = remoteMessage.getData().get(key);
                Log.d("Success", "Key: " + key + " Value: " + value);
            }
        }
        String msg = remoteMessage.getData().get("message");

        String title = remoteMessage.getData().get("title");

        sendNotification(msg, title);
    }

    private void sendNotification(String msg, String title) {
        NotificationManager mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent myintent = new Intent(this, MainActivity.class); //NotificationActivity
        myintent.putExtra("C3_login", getString(R.string.app_name));
        myintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent contentIntent = PendingIntent.getActivity(this, NOTIFICATION_MAIN, myintent, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle(title)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                            .setContentText(msg);
            mBuilder.setContentIntent(contentIntent);
            mBuilder.setAutoCancel(true);
            mNotificationManager.notify(NOTIFICATION_MAIN, mBuilder.build());
            NOTIFICATION_MAIN++;

           // AppConstants.notification =1;
        } catch (Exception e) {
            Log.e("Error", "EEE" + e);
        }
    }
}
