package mymap.com.map.View.activitites;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import mymap.com.map.Modules.DirectionFinderListener;
import mymap.com.map.Modules.NearPlacess;
import mymap.com.map.Modules.PlacesModel;
import mymap.com.map.Modules.Route;
import mymap.com.map.Presenter.PlacessSearchPresenter;
import mymap.com.map.R;
import mymap.com.map.View.mvp_view.PlacesMVP;
import mymap.com.map.View.utlites.AppConstants;
import mymap.com.map.View.utlites.Progressdialogue;
import mymap.com.map.View.utlites.SessionManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener,PlacesMVP.PlacesView {

    private GoogleMap mMap;
    View parentLayout;
    ArrayList<LatLng>loctions;
    ArrayList<PlacesModel>placesModelArrayList;
    PlacessSearchPresenter placessSearchPresenter;
    private SessionManager sessionManager;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_map);
        loctions=new ArrayList<>();
        parentLayout = findViewById(android.R.id.content);
        placesModelArrayList=new ArrayList<>();
        mContext=MapsActivity.this;
        sessionManager = new SessionManager(this);
        placessSearchPresenter=new PlacessSearchPresenter(this,mContext);
        sessionManager.storeSessionStringvalue(AppConstants.LOGIN_SESSION_NAME,AppConstants.APIKEY,"AIzaSyBm3Y4zoKAa1YthlPThwpuOVsGoqmNQjkY");
        sessionManager.storeSessionStringvalue(AppConstants.LOGIN_SESSION_NAME,AppConstants.Latitude,"11.016844");
        sessionManager.storeSessionStringvalue(AppConstants.LOGIN_SESSION_NAME,AppConstants.Longitude,"76.955833");
        Progressdialogue.showDialog(this);
        placessSearchPresenter.loadNearbyPlacess();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_map);
        mapFragment.getMapAsync(this);


    }




    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.e("On Map","Success");
        Log.e("Size @map", String.valueOf(loctions.size()));
        mMap = googleMap;



    }
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(parentLayout, message, Snackbar.LENGTH_SHORT);

        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

    @Override
    public void onDirectionFinderStart() {

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route) {

    }



    @Override
    public void setError(String status, String message) {
        Progressdialogue.dismiss();
        showSnackBar(message);

    }

    @Override
    public void showSuccessSyllabus(ArrayList<NearPlacess> nearPlacessArrayList) {
        Progressdialogue.dismiss();
        Log.e("Size", String.valueOf(nearPlacessArrayList.size()));

        for(int i=0;i<nearPlacessArrayList.size();i++){
            LatLng point = new LatLng(Double.parseDouble(nearPlacessArrayList.get(i).getLatitude()), Double.parseDouble(nearPlacessArrayList.get(i).getLangtitude()));
            loctions.add(point);
        }
        Log.e("Size", String.valueOf(loctions.size()));
       /* PolylineOptions polyOptions = new PolylineOptions();
        polyOptions.color(Color.BLUE);
        polyOptions.width(5);
        polyOptions.addAll(loctions);
        mMap.addPolyline(polyOptions);*/

        for(int i=0;i<loctions.size();i++){
            LatLng sydney = new LatLng(loctions.get(i).latitude,loctions.get(i).longitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title(nearPlacessArrayList.get(i).getPlaceName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        }

    }


}
