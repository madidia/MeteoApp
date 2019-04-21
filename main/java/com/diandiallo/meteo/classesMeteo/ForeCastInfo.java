package com.diandiallo.meteo.classesMeteo;

import org.json.JSONException;
import org.json.JSONObject;

public class ForeCastInfo {
    private Double latitude;
    private Double longitude;
    private String elevation;


    public ForeCastInfo(JSONObject js){
        try {
            latitude=js.getDouble("latitude");
            longitude=js.getDouble("longitude");
            elevation=js.getString("elevation");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}
