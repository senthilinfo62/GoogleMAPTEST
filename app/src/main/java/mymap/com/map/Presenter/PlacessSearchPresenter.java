package mymap.com.map.Presenter;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import mymap.com.map.Modules.NearPlacess;
import mymap.com.map.View.activitites.MapsActivity;
import mymap.com.map.View.mvp_view.PlacesMVP;
import mymap.com.map.View.utlites.ApiError;
import mymap.com.map.View.utlites.AppConstants;
import mymap.com.map.View.utlites.AppController;
import mymap.com.map.View.utlites.ConnectivityReceiver;
import mymap.com.map.View.utlites.SessionManager;

public class PlacessSearchPresenter implements PlacesMVP.Presenter {

    PlacesMVP.PlacesView mView;
    private SessionManager sessionManager;
    Context context;
    ArrayList<NearPlacess>nearPlacessArrayList=new ArrayList<>();
    public PlacessSearchPresenter(MapsActivity mapsActivity, Context mContext) {
        this.mView=mapsActivity;
        this.context=mContext;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void loadNearbyPlacess() {

        if (ConnectivityReceiver.isConnected()) {
            AppController.getInstance().clearAllQueue();
            Log.e("inside", "inside ");

            String key=sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME,AppConstants.APIKEY);
            String lat=sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME,AppConstants.Latitude);
            String lng=sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME,AppConstants.Longitude);
            String loc=lat+","+lng;
            final ANRequest request = AndroidNetworking.get(AppConstants.BASE_URL + AppConstants.SEARCHPLASE)
                   /* .addHeaders("Authorization", "Bearer " + token)*/
                    .addQueryParameter("key", key)
                    .addQueryParameter("location",loc)
                    .addQueryParameter("radius","5000")
                    .addQueryParameter("sensor","false")
                    .addQueryParameter("type","food")
                    .addQueryParameter("name","cruise")
                    .setPriority(Priority.HIGH)
                    .build();
            Log.e("Success", "URL : " + request.getUrl());
            request.getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.w("Response", String.valueOf(response));
                        String status = response.getString(AppConstants.status);
                        if (status.equalsIgnoreCase("OK")) {
                            String nextpage = "";
                            nextpage = response.getString("next_page_token");
                            JSONArray jsonArray = response.getJSONArray("results");


                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("geometry");
                                JSONObject location = jsonObject1.getJSONObject("location");
                                NearPlacess nearPlacess = new NearPlacess();
                                nearPlacess.setImageUrl(jsonObject.getString("icon"));
                                nearPlacess.setLatitude(location.getString("lat"));
                                nearPlacess.setLangtitude(location.getString("lng"));
                                nearPlacess.setPlaceName(jsonObject.getString("vicinity"));
                                nearPlacessArrayList.add(nearPlacess);

                            }
                            if (nextpage.isEmpty()) {
                                mView.showSuccessSyllabus(nearPlacessArrayList);
                            } else {
                                loadNextPage(nextpage);
                            }
                            //mView.showSuccessSyllabus(nearPlacessArrayList);
                        }else{
                            mView.setError(status,status);
                        }
                        } catch(Exception e){
                            e.printStackTrace();
                        }

                }

                @Override
                public void onError(ANError error) {
                    final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                    final Gson gson = builder.create();
                    if (error.getErrorCode() != 0) {
                        ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);
                        String message =apiError.getMessage();
                        int errorcode =apiError.getErrorCode();
                        Log.e("statusCode", String.valueOf(errorcode));
                        Log.e("message", message);

                        mView.setError(String.valueOf(error.getErrorCode()), apiError.getMessage());
                    } else {
                        mView.setError("error", AppConstants.COMMON_EXCEPTION);
                    }
                    error.printStackTrace();
                }
            });
        }else{
            mView.setError("error", AppConstants.NETWORK_EXCEPTION);
        }





    }

    private void loadNextPage(String nextpage) {
        if (ConnectivityReceiver.isConnected()) {
            AppController.getInstance().clearAllQueue();
            Log.e("inside", "inside ");

            String key=sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME,AppConstants.APIKEY);
            String lat=sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME,AppConstants.Latitude);
            String lng=sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME,AppConstants.Longitude);
            String loc=lat+","+lng;
            final ANRequest request = AndroidNetworking.get(AppConstants.BASE_URL + AppConstants.SEARCHPLASE)
                    /* .addHeaders("Authorization", "Bearer " + token)*/
                    .addQueryParameter("next_page_token",nextpage)
                    .addQueryParameter("key", key)
                    .addQueryParameter("location",loc)
                    .addQueryParameter("radius","5000")
                    .addQueryParameter("sensor","false")
                    .addQueryParameter("type","food")
                    .addQueryParameter("name","cruise")
                    .setPriority(Priority.HIGH)
                    .build();
            Log.e("Success", "URL : " + request.getUrl());
            request.getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.w("Response", String.valueOf(response));
                        String status = response.getString(AppConstants.status);
                        if (status.equalsIgnoreCase("OK")) {
                            String nextpage = "";
                            nextpage = response.getString("next_page_token");
                            JSONArray jsonArray = response.getJSONArray("results");


                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject jsonObject1 = jsonObject.getJSONObject("geometry");
                                JSONObject location = jsonObject1.getJSONObject("location");
                                NearPlacess nearPlacess = new NearPlacess();
                                nearPlacess.setImageUrl(jsonObject.getString("icon"));
                                nearPlacess.setLatitude(location.getString("lat"));
                                nearPlacess.setLangtitude(location.getString("lng"));
                                nearPlacess.setPlaceName(jsonObject.getString("vicinity"));
                                nearPlacessArrayList.add(nearPlacess);

                            }
                            if (nextpage.isEmpty()) {
                                mView.showSuccessSyllabus(nearPlacessArrayList);
                            } else {
                                loadNextPage(nextpage);
                            }
                           // mView.showSuccessSyllabus(nearPlacessArrayList);
                        }else {
                            mView.setError(status,status);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(ANError error) {
                    final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                    final Gson gson = builder.create();
                    if (error.getErrorCode() != 0) {
                        ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);
                        String message =apiError.getMessage();
                        int errorcode =apiError.getErrorCode();
                        Log.e("statusCode", String.valueOf(errorcode));
                        Log.e("message", message);

                        mView.setError(String.valueOf(error.getErrorCode()), apiError.getMessage());
                    } else {
                        mView.setError("error", AppConstants.COMMON_EXCEPTION);
                    }
                    error.printStackTrace();
                }
            });
        }else{
            mView.setError("error", AppConstants.NETWORK_EXCEPTION);
        }

    }
}
