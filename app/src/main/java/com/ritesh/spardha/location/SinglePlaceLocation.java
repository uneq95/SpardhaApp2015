package com.ritesh.spardha.location;

/**
 * Created by ritesh_kumar on 16-May-15.
 */
public class SinglePlaceLocation {

    public String placeName,placeLongitude,placeLatitude;

    public SinglePlaceLocation(String placeName,String placeLongitude,String placeLatitude){
        this.placeName=placeName;
        this.placeLongitude=placeLongitude;
        this.placeLatitude=placeLatitude;
    }

    public void setPlaceName(String placeName){
        this.placeName=placeName;
    }
    public void setPlaceLongitude(String placeLongitude){
        this.placeLongitude=placeLongitude;
    }
    public void setPlaceLatitude(String placeLatitude){
        this.placeLatitude=placeLatitude;
    }

    public String getPlaceName(){
        return placeName;
    }
    public String getPlaceLongitude(){
        return placeLongitude;
    }
    public String getPlaceLatitude(){
        return placeLatitude;
    }

}
