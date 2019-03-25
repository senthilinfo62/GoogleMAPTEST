package mymap.com.map.View.utlites;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class AppConstants {

    //App Url
    public static final String BASE_URL="https://maps.googleapis.com/maps/api/";
    public static final String SEARCHPLASE="place/search/json";
    public static final String APIKEY="_googlekey";
    public static final String Latitude="_Lat";
    public static final String Longitude="_Long";
    public static final String radius="_radius";

    //Error Hit
    public static final String LOGIN_SESSION_NAME = "login_session";
    public static final String LOGIN_SESSION_USER_ID = "_id";
    public static final String TUTORIAL_SESSION_NAME = "tutorial_session";
    public static final String COMMON_EXCEPTION = "Something went wrong!!!";
    public static final String NETWORK_EXCEPTION = "Network error. Please check your Network connection..";
    //Response
    public static final String message="message";
    public static final String success="success";
    public static final String status="status";
    public static final String token="token";
    public static final String user="user";

    public static final int ALL_REQUEST_CODE = 0;
    public static final String[] ALL_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CAMERA
    };
    public static final String[] CONTACT_PERMISSIONS =
            {
                    Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS
            };

    public static final String LOGIN_SESSION_OTP = "OTP";
    public static final String LOGIN_SESSION_OTP_STATUS = "OTP_Status";
    public static final String LOGIN_SESSION_USER_DEVICETOKEN = "";
    public static String mapAddress = "";
    public static int appTracling = 0;
    public static int addChild = 0;
    public static final String CAMERA_PERMISSIONS =
            Manifest.permission.CAMERA;
    public static final String[] LOCATION_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    public static final String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void turnGpsOn(final Context context) {
        final AppPermissions mRuntimePermission = new AppPermissions((AppCompatActivity) context);
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            mRuntimePermission.requestPermission(AppConstants.ALL_PERMISSIONS, AppConstants.ALL_REQUEST_CODE);
        }
        else {
            Toast.makeText(context, "Gps enabled", Toast.LENGTH_SHORT).show();
        }
        return;
    }

}
