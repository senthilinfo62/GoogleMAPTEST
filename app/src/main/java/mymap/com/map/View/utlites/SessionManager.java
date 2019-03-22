package mymap.com.map.View.utlites;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

public class SessionManager {
    private Context mContext;

    public SessionManager(Context context) {
        mContext = context;

    }

    public String getSessionStringValue(String sessionName, String sessionKey) {
        SharedPreferences settings = getSession(sessionName);
        return settings.getString(sessionKey, null);
    }

    private SharedPreferences getSession(String sessionName) {
        int PRIVATE_MODE = 0;
        return mContext.getSharedPreferences(sessionName, PRIVATE_MODE);
    }

    public int getSessionIntValue(String sessionName, String sessionKey) {
        SharedPreferences settings = getSession(sessionName);
        // Reading integer value from SharedPreferences
        return settings.getInt(sessionKey, 0);
    }



    public void storeSessionStringvalue(String sessionName, String key, String value) {
        SharedPreferences settings = getSession(sessionName);
        // Writing String data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public void storeSessionIntvalue(String sessionName, String key, Resources resources, int value) {
        SharedPreferences settings = getSession(sessionName);
        // Writing integer data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public boolean isLogin() {
        SharedPreferences settings = getSession(AppConstants.LOGIN_SESSION_NAME);
        // Reading integer value from SharedPreferences
        return settings.getInt(AppConstants.LOGIN_SESSION_USER_ID, 0) > 0;
    }

    public String getUserID() {
        SharedPreferences settings = getSession(AppConstants.LOGIN_SESSION_NAME);
        // Reading integer value from SharedPreferences
        Log.w("Success", "UserID::: " + settings.getInt(AppConstants.LOGIN_SESSION_USER_ID, -1));
        return settings.getInt(AppConstants.LOGIN_SESSION_USER_ID, -1) > 0 ? String.valueOf(settings.getInt(AppConstants.LOGIN_SESSION_USER_ID, -1)) : "";
    }

    public void callLogout() {
        SharedPreferences settings = getSession(AppConstants.LOGIN_SESSION_NAME);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }

    public int isExist() {
        SharedPreferences settings = getSession(AppConstants.TUTORIAL_SESSION_NAME);
        return settings.getAll().size();
    }

    public int isExistLogin() {
        SharedPreferences settings = getSession(AppConstants.LOGIN_SESSION_NAME);
        return settings.getAll().size();
    }

}
