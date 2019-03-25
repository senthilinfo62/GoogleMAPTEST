package mymap.com.map.View.activitites;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;




import mymap.com.map.R;
import mymap.com.map.View.utlites.AppConstants;
import mymap.com.map.View.utlites.AppPermissions;
import mymap.com.map.View.utlites.SessionManager;

/**
 * Created by Senthil on 25-04-2018.
 */

public class TutorialActivity extends AppCompatActivity implements View.OnClickListener {

    TextView nextbtn;
    private SessionManager sessionManager;
    private AppPermissions mRuntimePermission;
    LocationManager locationManager;
    boolean gps_enabled = false;
    boolean network_enabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mRuntimePermission = new AppPermissions(this);
        locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Enable Location");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    AppConstants.turnGpsOn(TutorialActivity.this);
                    //get gps
                }
            });

            dialog.show();
        }

        if (mRuntimePermission.hasPermission(AppConstants.ALL_PERMISSIONS)) {
            // Toast.makeText(this, "All permission already given", Toast.LENGTH_SHORT).show();
        } else {
            mRuntimePermission.requestPermission(AppConstants.ALL_PERMISSIONS, AppConstants.ALL_REQUEST_CODE);
        }
        nextbtn = (TextView) findViewById(R.id.editTextDestination);
        sessionManager = new SessionManager(this);

        nextbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == nextbtn) {
            if(sessionManager.isExistLogin()==0){
                AppConstants.appTracling=1;
                startActivity(new Intent(TutorialActivity.this, MapsActivity.class));
                finish();
            }else{

                String otp_status = sessionManager.getSessionStringValue(AppConstants.LOGIN_SESSION_NAME, AppConstants.LOGIN_SESSION_OTP_STATUS);
                if(otp_status.equalsIgnoreCase("true")){
                    AppConstants.appTracling=0;
                    startActivity(new Intent(TutorialActivity.this, MapsActivity_Route.class));
                    /*overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);*/
                    finish();
                }else{
                    AppConstants.appTracling=1;
                    startActivity(new Intent(TutorialActivity.this, MapsActivity.class));
                    finish();
                }

            }
        }
    }
}
