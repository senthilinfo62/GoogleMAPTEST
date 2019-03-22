package mymap.com.map.View.activitites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import mymap.com.map.R;
import mymap.com.map.View.utlites.AppConstants;
import mymap.com.map.View.utlites.AppPermissions;

public class MainActivity extends AppCompatActivity {
       Button MutipleLoction,Distance;
    private AppPermissions mRuntimePermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRuntimePermission = new AppPermissions(this);

        if (mRuntimePermission.hasPermission(AppConstants.ALL_PERMISSIONS)) {
            // Toast.makeText(this, "All permission already given", Toast.LENGTH_SHORT).show();
            Log.d("Success", "All permission already given");
        } else {
            mRuntimePermission.requestPermission(AppConstants.ALL_PERMISSIONS, AppConstants.ALL_REQUEST_CODE);
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MutipleLoction=findViewById(R.id.MutipleLoction);
        Distance=findViewById(R.id.Distance);
        MutipleLoction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        Distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapsActivity_Route.class);
                startActivity(intent);
            }
        });

    }

}
