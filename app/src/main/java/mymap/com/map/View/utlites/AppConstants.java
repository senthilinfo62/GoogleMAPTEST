package mymap.com.map.View.utlites;

import android.Manifest;

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



    public static final String CAMERA_PERMISSIONS =
            Manifest.permission.CAMERA;
    public static final String[] LOCATION_PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    public static final String[] STORAGE_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
}
