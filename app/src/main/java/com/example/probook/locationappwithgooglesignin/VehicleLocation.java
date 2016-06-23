package com.example.probook.locationappwithgooglesignin;

/**
 * Created by probook on 6/4/2016.
 */
public class VehicleLocation {

    private String Latitude;
    private String Longitude;


    public VehicleLocation() {

    }

    public VehicleLocation(String latitude, String longitude) {
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }
}

