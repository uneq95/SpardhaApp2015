package com.ritesh.spardha.location;

import java.util.HashMap;

/**
 * Created by ritesh_kumar on 16-May-15.
 */
public class IITPlacesOfInterest {

    static HashMap<String, SinglePlaceLocation> IITPlaces;

    public IITPlacesOfInterest() {
        IITPlaces = new HashMap<String, SinglePlaceLocation>();
        setLocations();
    }

    public HashMap<String, SinglePlaceLocation> getIITPlaces() {
        return IITPlaces;
    }

    private void setLocations() {
        IITPlaces.put("Hostel", new SinglePlaceLocation("Visweshwarayya", "25.262834","82.983902"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("SN Bose", "25.263125","82.983886" ));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Aryabhatta", "25.264012", "82.984352"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Ramanujan", "25.263124", "82.984826"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Dhanrajgiri", "25.263912", "82.986277"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Morvi", "25.265025", "82.986382"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("CV Raman", "25.265865", "82.986481"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Rajputana", "25.262418", "82.986349"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Limbdi", "25.261312", "82.986524"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("SC De", "25.260184", "82.986859"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Vivekanand", "25.259272","82.987251"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Vishwakarma", "25.257690", "82.985695"));
        IITPlaces.put("Hostel", new SinglePlaceLocation("Old GSMC", "25.260601", "82.983670"));
        //IITPlaces.put("Hostel", new SinglePlaceLocation("New GSMC Extn.", "", ""));
        IITPlaces.put("ATMs", new SinglePlaceLocation("SBI Hyderabad Gate", "25.261717", "82.981647"));
        IITPlaces.put("ATMs", new SinglePlaceLocation("Axis Bank Hyderabad Gate", "25.261593", "82.981642"));
        IITPlaces.put("ATMs", new SinglePlaceLocation("SBI-BHU", "25.263738", "82.994762"));
        IITPlaces.put("Sports Venues", new SinglePlaceLocation("IIT-BHU Gymkhana", "25.259176", "82.989229"));
        IITPlaces.put("Sports Venues", new SinglePlaceLocation("Rajputana Ground", "25.262499", "82.986691"));
        IITPlaces.put("Sports Venues", new SinglePlaceLocation("Amphitheatre", "25.265730", "82.995231"));
        IITPlaces.put("Sports Venues", new SinglePlaceLocation("Arun Dream Village(ADV)", "25.259040", "82.989331"));
        IITPlaces.put("Hospital", new SinglePlaceLocation("Sir Sunderlal Hospital", "25.275727", "83.000570"));
        IITPlaces.put("Other Places", new SinglePlaceLocation("DG Corner", "25.263023", "82.986498"));
        IITPlaces.put("Other Places", new SinglePlaceLocation("Limbdi Corner", "25.260777", "82.986884"));
        IITPlaces.put("Other Places", new SinglePlaceLocation("Vishvanath Temple", "25.265676", "82.989475"));
        IITPlaces.put("Other Places", new SinglePlaceLocation("Swatantrata Bhawan", "25.260636", "82.993676"));


    }

}
