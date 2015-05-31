package com.ritesh.spardha.location;

/**
 * Created by ritesh_kumar on 16-May-15.
 */
public class SinglePlaceLocation {

    public String placeName,placeLongitude,placeLatitude;
    public int categoryInt;

    public SinglePlaceLocation(int category,String placeName,String placeLongitude,String placeLatitude){
        this.placeName=placeName;
        this.placeLongitude=placeLongitude;
        this.placeLatitude=placeLatitude;
        this.categoryInt=category;
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
    public void setPlaceCategory(int category){
        this.categoryInt=category;
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
    public int getPlaceCategory(){
        return categoryInt;
    }

}
