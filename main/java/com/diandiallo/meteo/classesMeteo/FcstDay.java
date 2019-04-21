package com.diandiallo.meteo.classesMeteo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class FcstDay {

    private String date;
    private String dayShort;
    private String dayLong;
    private Integer tmin;
    private Integer tmax;
    private String condition;
    private String conditionKey;
    private String icon;
    private String iconBig;
    private ArrayList<DonneesHoraires> donneesHoraires=new ArrayList<>();

    private String hourly;

    JSONObject json;

    public FcstDay(JSONObject js){
        try {
            json=js;
            date=js.getString("date");
            dayShort=js.getString("day_short");
            dayLong=js.getString("day_long");
            tmin=js.getInt("tmin");
            tmax=js.getInt("tmax");
            condition=js.getString("condition");
            conditionKey=js.getString("condition_key");
            icon=js.getString("icon");
            iconBig=js.getString("icon_big");

            // on recupere les donnees horaires
            for(int i=0;i<24;i++){
                donneesHoraires.add(this.getHourlyData(i+"H00"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayShort() {
        return dayShort;
    }

    public void setDayShort(String dayShort) {
        this.dayShort = dayShort;
    }

    public String getDayLong() {
        return dayLong;
    }

    public void setDayLong(String dayLong) {
        this.dayLong = dayLong;
    }

    public Integer getTmin() {
        return tmin;
    }

    public void setTmin(Integer tmin) {
        this.tmin = tmin;
    }

    public Integer getTmax() {
        return tmax;
    }

    public void setTmax(Integer tmax) {
        this.tmax = tmax;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionKey() {
        return conditionKey;
    }

    public void setConditionKey(String conditionKey) {
        this.conditionKey = conditionKey;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconBig() {
        return iconBig;
    }

    public void setIconBig(String iconBig) {
        this.iconBig = iconBig;
    }

    public ArrayList<DonneesHoraires> getDonneesHoraires(){
        return this.donneesHoraires;
    }

    public void setDonneesHoraires(ArrayList<DonneesHoraires> mesDonneesHoraires){
        this.donneesHoraires=mesDonneesHoraires;
    }


    public DonneesHoraires getHourlyData(String hour) {

        try {
            return new DonneesHoraires(json.getJSONObject("hourly_data").getJSONObject(hour),hour);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String jSonToString(){
        return json.toString();
    }
}
