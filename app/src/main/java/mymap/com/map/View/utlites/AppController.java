package mymap.com.map.View.utlites;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import android.support.multidex.MultiDex;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.internal.ANRequestQueue;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Cogniti Digital Solutions.
 */

public class AppController extends Application {

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.initialize(mInstance, okHttpClient);

        Intent fcmTokenID = new Intent(this, FCMIDListernerService.class);
        startService(fcmTokenID);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public void clearQueue(String tag) {
        ANRequestQueue.getInstance().cancelRequestWithGivenTag(tag, false);
        Log.w("Success", "" + tag + " Queue Cleared");
    }

    public void clearAllQueue() {
        ANRequestQueue.getInstance().cancelAll(false);
        Log.w("Success", "All Queue Cleared");
    }



    public static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }


}
