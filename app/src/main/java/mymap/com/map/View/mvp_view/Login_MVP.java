package mymap.com.map.View.mvp_view;

import org.json.JSONObject;

/**
 * Created by Senthil on 26-04-2018.
 */

public interface Login_MVP {
    public interface Views{
         void showSignupSuccess(String message, String status, String id, String token, String cart_count);
         void setError(String error);
        void LoginResponse(String success, String message, JSONObject user_detail);
        void showSuccess(String status, String message);
        void  versionSuccess(String status, String message);
        void versionError(String status, String message);

    }
    public interface Login{
         void Loginurl(JSONObject LoginUrl);
        void  TokenUpdate(JSONObject jsonObject);
        void  VersionCheck();
    }
}
