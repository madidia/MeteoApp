package com.diandiallo.meteo.classesMeteo;

import org.json.JSONException;
import org.json.JSONObject;

public class Ville {
    private String name;
    private String country;
    private String url;


    public Ville(String name,String country, String url){
        this.name=name;
        this.country=country;
        this.url=url;
    }

    public Ville(JSONObject js){
        try {
            name=js.getString("name");
            country=js.getString("country");
            url=js.getString("url");
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
