package com.ritesh.spardha.location;

import java.util.ArrayList;

/**
 * Created by ritesh_kumar on 16-May-15.
 */
public class IITPlacesOfInterest {

    static ArrayList<SinglePlaceLocation> IITPlaces;
    public static String[] Category = new String[]{"Hostel", "ATM", "Sports Venues", "Hospital", "Other Places"};

    public IITPlacesOfInterest() {
        IITPlaces = new ArrayList<>();
        setLocations();
    }

    public ArrayList<SinglePlaceLocation> getIITPlaces() {
        return IITPlaces;
    }

    private void setLocations() {
        IITPlaces.add(new SinglePlaceLocation(0, "Visweshwarayya", "25.262834", "82.983902"));
        IITPlaces.add(new SinglePlaceLocation(0, "SN Bose", "25.263125", "82.983886"));
        IITPlaces.add(new SinglePlaceLocation(0, "Aryabhatta", "25.264012", "82.984352"));
        IITPlaces.add(new SinglePlaceLocation(0, "Ramanujan", "25.263124", "82.984826"));
        IITPlaces.add(new SinglePlaceLocation(0, "Dhanrajgiri", "25.263912", "82.986277"));
        IITPlaces.add(new SinglePlaceLocation(0, "Morvi", "25.265025", "82.986382"));
        IITPlaces.add(new SinglePlaceLocation(0, "CV Raman", "25.265865", "82.986481"));
        IITPlaces.add(new SinglePlaceLocation(0, "Rajputana", "25.262418", "82.986349"));
        IITPlaces.add(new SinglePlaceLocation(0, "Limbdi", "25.261312", "82.986524"));
        IITPlaces.add(new SinglePlaceLocation(0, "SC De", "25.260184", "82.986859"));
        IITPlaces.add(new SinglePlaceLocation(0, "Vivekanand", "25.259272", "82.987251"));
        IITPlaces.add(new SinglePlaceLocation(0, "Vishwakarma", "25.257690", "82.985695"));
        IITPlaces.add(new SinglePlaceLocation(0, "Old GSMC", "25.260601", "82.983670"));
        //IITPlaces.add( new SinglePlaceLocation(,"New GSMC Extn.", "", ""));
        IITPlaces.add(new SinglePlaceLocation(1, "SBI Hyderabad Gate", "25.261717", "82.981647"));
        IITPlaces.add(new SinglePlaceLocation(1, "Axis Bank Hyderabad Gate", "25.261593", "82.981642"));
        IITPlaces.add(new SinglePlaceLocation(1, "SBI-BHU", "25.263738", "82.994762"));
        IITPlaces.add(new SinglePlaceLocation(2, "IIT-BHU Gymkhana", "25.259176", "82.989229"));
        IITPlaces.add(new SinglePlaceLocation(2, "Rajputana Ground", "25.262499", "82.986691"));
        IITPlaces.add(new SinglePlaceLocation(2, "Amphitheatre", "25.265730", "82.995231"));
        IITPlaces.add(new SinglePlaceLocation(2, "Arun Dream Village(ADV)", "25.259040", "82.989331"));
        IITPlaces.add(new SinglePlaceLocation(3, "Sir Sunderlal Hospital", "25.275727", "83.000570"));
        IITPlaces.add(new SinglePlaceLocation(4, "DG Corner", "25.263023", "82.986498"));
        IITPlaces.add(new SinglePlaceLocation(4, "Limbdi Corner", "25.260777", "82.986884"));
        IITPlaces.add(new SinglePlaceLocation(4, "Vishvanath Temple", "25.265676", "82.989475"));
        IITPlaces.add(new SinglePlaceLocation(4, "Swatantrata Bhawan", "25.260636", "82.993676"));
    }


}
