package mymap.com.map.Modules;

public class PlacesModel {
    String Place;
    String lat;

    public PlacesModel(String place, String lat, String lang) {
        Place = place;
        this.lat = lat;
        this.lang = lang;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    String lang;
}
