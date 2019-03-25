package mymap.com.map.Presenter;

import android.content.Context;


import org.json.JSONException;
import org.json.JSONObject;

import mymap.com.map.View.mvp_view.Login_MVP;
import mymap.com.map.View.utlites.AppConstants;
import mymap.com.map.View.utlites.ConnectivityReceiver;
import mymap.com.map.View.utlites.SessionManager;

/**
 * Created by CDS123 on 26-04-2018.
 */

public class LoginPresenter implements Login_MVP.Login {
    private Login_MVP.Views mView;
    private SessionManager sessionManager;
    Context mcontext;
    JSONObject jsonObject1;

    public LoginPresenter(Login_MVP.Views view, Context context) {
        mView = view;
        mcontext = context;
        sessionManager = new SessionManager(context);
    }

    @Override
    public void Loginurl(JSONObject LoginUrl) {
       /* if (ConnectivityReceiver.isConnected()) {
            Log.w("Success", "Parameter::: " + LoginUrl);
           // AppController.getInstance().clearAllQueue();
            final ANRequest request= AndroidNetworking.post(AppConstants.LIVE_URL + AppConstants.PARENT_URL + AppConstants.LOGIN)
                    .addJSONObjectBody(LoginUrl)
                    .setPriority(Priority.HIGH)
                    .build();
            Log.v("Success","URL:::"+request.getUrl());
            request.getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Success","LoginResponse"+response);
                    try {
                        String success = response.getString(AppConstants.SUCCESS);
                        String message = response.getString(AppConstants.MESSAGE);

                        JSONObject user_detail = new JSONObject();
                        if(success.equals("true")){
                            user_detail = response.getJSONObject(AppConstants.PARENT);
                            mView.LoginResponse(success,message,user_detail);
                        } else {
                            mView.setError(message);
                        }
                    }catch (JSONException e) {
                        Log.d("ERROR","errorrrr"+e);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(ANError error) {
                    if (error.getErrorCode() != 0) {
                        mView.setError(error.getErrorDetail());
                    } else {
                        Log.e("Success", "onError errorDetail : " + error.getErrorDetail());
                        mView.setError(AppConstants.COMMON_EXCEPTION);
                    }
                    error.printStackTrace();
                }
            });
        } else{
            mView.setError(AppConstants.NETWORK_ERROR);
        }*/
    }

    @Override
    public void TokenUpdate(JSONObject jsonObject) {
      /*  if (ConnectivityReceiver.isConnected()) {
            // AppController.getInstance().clearAllQueue();

            jsonObject1 = jsonObject;
            Log.e("jsonObject1", String.valueOf(jsonObject));
            String id = sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME, AppConstants.LOGIN_SESSION_USER_ID);
            Log.e("ID",id);
            String token = sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME, AppConstants.LOGIN_SESSION_USER_DEVICETOKEN);
            final ANRequest request = AndroidNetworking.put(AppConstants.LIVE_URL + AppConstants.UpdateToken+id)
                    .addJSONObjectBody(jsonObject)
                    .setPriority(Priority.HIGH)
                    .build();
            Log.e("Success", "URL : " + request.getUrl());
            request.getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.w("Response :",  response.toString(4));
                        String success = response.getString(AppConstants.SUCCESS);
                        String message = response.getString(AppConstants.MESSAGE);

                        if (success.equals("true")) {
                            mView.showSuccess(success,message);
                        } else {
                            mView.setError(message);
                            TokenUpdate(jsonObject1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(ANError error) {
                    if (error.getErrorCode() != 0) {
                        mView.setError(error.getErrorDetail());
                    } else {
                        Log.e("Success", "onError errorDetail : " + error.getErrorDetail());
                        mView.setError(AppConstants.COMMON_EXCEPTION);
                    }
                    error.printStackTrace();
                }
            });
        }*/

    }

    @Override
    public void VersionCheck() {
        if (ConnectivityReceiver.isConnected()) {
/*

            // AppController.getInstance().clearAllQueue();

            final ANRequest request = AndroidNetworking.get(AppConstants.LIVE_URL+ AppConstants.versionCheck)
                    .setPriority(Priority.HIGH)
                    .build();
            Log.e("Success", "URL : " + request.getUrl());
            request.getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.w("Response :",  response.toString(4));
                        String status = response.getString(AppConstants.SUCCESS);
                        String message = response.getString(AppConstants.MESSAGE);
                        String version="1.0";
                        if (status.equals("true")) {

                            try {
                                PackageInfo pInfo = mcontext.getPackageManager().getPackageInfo(mcontext.getPackageName(), 0);
                                version = pInfo.versionName;
                                Log.e("version",version);
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }

                            if(response.getString("version").equals("1.0")){
                                mView.versionSuccess(status,message);
                            }else{
                                mView.versionError(status,message);
                            }


                        } else {
                            mView.setError(message);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(ANError error) {
                    final GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
                    final Gson gson = builder.create();
                   */
/* if (error.getErrorCode() != 0) {
                        ApiError apiError = gson.fromJson(error.getErrorBody(), ApiError.class);
                        String message =apiError.getMessage();
                        int errorcode =apiError.getErrorCode();
                        Log.e("statusCode", String.valueOf(errorcode));
                        Log.e("message", message);

                        mView.setError(String.valueOf(error.getErrorCode()), apiError.getMessage());
                    } else {
                        mView.setError("error", AppConstants.COMMON_EXCEPTION);
                    }*//*

                    error.printStackTrace();
                }
            });
        }else {
            mView.setError( AppConstants.NETWORK_ERROR);
        }
*/

        }

    }

}
