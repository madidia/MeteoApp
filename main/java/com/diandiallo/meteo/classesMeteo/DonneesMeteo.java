package com.diandiallo.meteo.classesMeteo;

import org.json.JSONException;
import org.json.JSONObject;

public class DonneesMeteo {

    private CityInfo cityInfo;
    private ForeCastInfo forecastInfo;
    private CurrentCondition currentCondition;
    private FcstDay fcstDay0;
    private FcstDay fcstDay1;
    private FcstDay fcstDay2;
    private FcstDay fcstDay3;
    private FcstDay fcstDay4;



    public DonneesMeteo(JSONObject js){
        try {
            cityInfo=new CityInfo(new JSONObject(js.getString("city_info")));
            forecastInfo=new ForeCastInfo(new JSONObject(js.getString("forecast_info")));
            currentCondition=new CurrentCondition(new JSONObject(js.getString("current_condition")));
            fcstDay0=new FcstDay(new JSONObject(js.getString("fcst_day_0")));
            fcstDay1=new FcstDay(new JSONObject(js.getString("fcst_day_1")));
            fcstDay2=new FcstDay(new JSONObject(js.getString("fcst_day_2")));
            fcstDay3=new FcstDay(new JSONObject(js.getString("fcst_day_3")));
            fcstDay4=new FcstDay(new JSONObject(js.getString("fcst_day_4")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ForeCastInfo getForecastInfo() {
        return forecastInfo;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public FcstDay getFcstDay0() {
        return fcstDay0;
    }

    public void setFcstDay0(FcstDay fcstDay0) {
        this.fcstDay0 = fcstDay0;
    }

    public FcstDay getFcstDay1() {
        return fcstDay1;
    }

    public void setFcstDay1(FcstDay fcstDay1) {
        this.fcstDay1 = fcstDay1;
    }

    public FcstDay getFcstDay2() {
        return fcstDay2;
    }

    public void setFcstDay2(FcstDay fcstDay2) {
        this.fcstDay2 = fcstDay2;
    }

    public FcstDay getFcstDay3() {
        return fcstDay3;
    }

    public void setFcstDay3(FcstDay fcstDay3) {
        this.fcstDay3 = fcstDay3;
    }

    public FcstDay getFcstDay4() {
        return fcstDay4;
    }

    public void setFcstDay4(FcstDay fcstDay4) {
        this.fcstDay4 = fcstDay4;
    }
}
