package com.diandiallo.meteo.classesMeteo;

import org.json.JSONException;
import org.json.JSONObject;

public class CurrentCondition {

    private String date;
    private String hour;
    private Integer tmp;
    private Integer wndSpd;
    private Integer wndGust;
    private String wndDir;
    private Double pressure;
    private Integer humidity;
    private String condition;
    private String conditionKey;
    private String icon;
    private String iconBig;

    public CurrentCondition(JSONObject js){

        try {
            date=js.getString("date");
            hour=js.getString("hour");
            tmp=js.getInt("tmp");
            wndSpd=js.getInt("wnd_spd");
            wndGust=js.getInt("wnd_gust");
            wndDir=js.getString("wnd_dir");
            pressure=js.getDouble("pressure");
            humidity=js.getInt("humidity");
            condition=js.getString("condition");
            conditionKey=js.getString("condition_key");
            icon=js.getString("icon");
            iconBig=js.getString("icon_big");
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getTmp() {
        return tmp;
    }

    public void setTmp(Integer tmp) {
        this.tmp = tmp;
    }

    public Integer getWndSpd() {
        return wndSpd;
    }

    public void setWndSpd(Integer wndSpd) {
        this.wndSpd = wndSpd;
    }

    public Integer getWndGust() {
        return wndGust;
    }

    public void setWndGust(Integer wndGust) {
        this.wndGust = wndGust;
    }

    public String getWndDir() {
        return wndDir;
    }

    public void setWndDir(String wndDir) {
        this.wndDir = wndDir;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
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



}
