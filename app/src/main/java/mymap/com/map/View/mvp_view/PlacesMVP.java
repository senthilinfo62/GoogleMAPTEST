package mymap.com.map.View.mvp_view;

import java.util.ArrayList;

import mymap.com.map.Modules.NearPlacess;

public interface PlacesMVP {
    interface PlacesView {
        void setError(String status, String message);
        void showSuccessSyllabus(ArrayList<NearPlacess> nearPlacessArrayList);

    }
    interface Presenter{
        void loadNearbyPlacess();

    }

}
