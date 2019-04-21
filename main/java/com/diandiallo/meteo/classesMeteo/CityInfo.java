package com.diandiallo.meteo.classesMeteo;

import org.json.JSONException;
import org.json.JSONObject;

public class CityInfo {

    private String name;
    private String country;
    private String latitude;
    private String longitude;
    private String elevation;
    private String sunset;
    private String sunrise;

    public CityInfo(JSONObject js) {

        try {
            name = js.getString("name");
            country = js.getString("country");
            latitude = js.getString("latitude");
            longitude = js.getString("longitude");
            elevation = js.getString("elevation");
            sunset = js.getString("sunset");
            sunrise = js.getString("sunrise");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

}
