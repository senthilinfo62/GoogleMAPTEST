package mymap.com.map.View.utlites;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Senthil on 10-02-2018.
 */

public class FCMIDListernerService extends FirebaseInstanceIdService {

    public FCMIDListernerService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        Log.d("Success", "Comes token class");
        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.w("Success", "Token::: " + fcmToken);
    }
}
