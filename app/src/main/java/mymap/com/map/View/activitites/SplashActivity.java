package mymap.com.map.View.activitites;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import com.google.firebase.iid.FirebaseInstanceId;


import org.json.JSONException;
import org.json.JSONObject;

import mymap.com.map.Presenter.LoginPresenter;
import mymap.com.map.R;
import mymap.com.map.View.mvp_view.Login_MVP;
import mymap.com.map.View.utlites.AppConstants;
import mymap.com.map.View.utlites.FCMIDListernerService;
import mymap.com.map.View.utlites.SessionManager;

/**
 * Created by Senthil on 26-04-2018.
 */

public class SplashActivity extends AppCompatActivity implements Login_MVP.Views{
    private final int SPLASH_DISPLAY_LENGTH = 100;
    public static String fcmToken;
    SessionManager sessionManager;
    LoginPresenter loginPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        sessionManager = new SessionManager(this);
        Intent fcmTokenID = new Intent(this, FCMIDListernerService.class);
        startService(fcmTokenID);

        loginPresenter = new LoginPresenter(this, this);

       fcmToken = FirebaseInstanceId.getInstance().getToken();


        loginPresenter.VersionCheck();

    }

    @Override
    public void showSignupSuccess(String message, String status, String id, String token, String cart_count) {

    }

    @Override
    public void setError(String error) {

    }

    @Override
    public void LoginResponse(String success, String message, JSONObject user_detail) {

    }

    @Override
    public void showSuccess(String status, String message) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, TutorialActivity.class));
                finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void versionSuccess(String status, String message) {
        if(sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME, AppConstants.LOGIN_SESSION_USER_ID).equals("0")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(SplashActivity.this, TutorialActivity.class));
                    finish();

                }
            }, SPLASH_DISPLAY_LENGTH);

        }else{


            sessionManager.storeSessionStringvalue(AppConstants.LOGIN_SESSION_NAME, AppConstants.LOGIN_SESSION_USER_DEVICETOKEN,fcmToken);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("deviceToken",fcmToken);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            loginPresenter.TokenUpdate(jsonObject);
        }
    }

    @Override
    public void versionError(String status, String message) {
        new MaterialDialog.Builder(this)
                .title(R.string.updateTitle)
                .content(R.string.updateContent)
                .positiveText(R.string.updateBtn)
                .negativeText(R.string.cancelBtn)
                .cancelable(false)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        // TODO
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {

                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {

                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        // TODO
                        finish();
                    }
                })
                .show();

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
