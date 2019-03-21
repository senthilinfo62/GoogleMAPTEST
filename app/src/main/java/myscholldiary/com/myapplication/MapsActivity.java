package myscholldiary.com.myapplication;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import myscholldiary.com.myapplication.Modules.DirectionFinderListener;
import myscholldiary.com.myapplication.Modules.Route;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;

    ArrayList<LatLng>loctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        loctions=new ArrayList<>();
        addLocations();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void addLocations() {
        String coord1lat = "11.016844";
        String coord1lng = "76.955833";
        String coord2lat = "10.579000";
        String coord2lng = "77.250420";
        String coord3lat = "10.999667";
        String coord3lng = "77.280739";
        String coord4lat = "10.088933";
        String coord4lng = "77.059525";
        String coord5lat = "13.082680";
        String coord5lng = "80.270721";
        String coord6lat = "9.925201";
        String coord6lng = "78.119774";
        String coord7lat = "10.786730";
        String coord7lng = "76.654793";
        String coord8lat = "11.219439";
        String coord8lng = "78.167725";
        String coord9lat = "10.779970";
        String coord9lng = "78.756264";
        String[] coordinatesStringList = {coord1lat, coord1lng, coord2lat, coord2lng,coord3lat,coord3lng,coord4lat,coord4lng,coord5lat,coord5lng
        ,coord6lat,coord6lng,coord7lat,coord7lng,coord8lat,coord8lng,coord9lat,coord9lng};


        for(int i=0; i<coordinatesStringList.length; i+=2){
            LatLng point = new LatLng(Double.parseDouble(coordinatesStringList[i]), Double.parseDouble(coordinatesStringList[i+1]));
            loctions.add(point);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        PolylineOptions polyOptions = new PolylineOptions();
        polyOptions.color(Color.BLUE);
        polyOptions.width(5);
        polyOptions.addAll(loctions);
        mMap.addPolyline(polyOptions);

        for(int i=0;i<loctions.size();i++){
            LatLng sydney = new LatLng(loctions.get(i).latitude,loctions.get(i).longitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title(String.valueOf(i)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        }



    }

    @Override
    public void onDirectionFinderStart() {

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> route) {

    }
}
