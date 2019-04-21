package com.diandiallo.meteo.classesMeteo;

import org.json.JSONException;
import org.json.JSONObject;

public class DonneesHoraires {

    private String icon;
    private String condition;
    private String conditionKey;
    private Double tmp2m;
    private Double dpt2m;
    private Double wndchill2m;
    private Double humidex;
    private Integer rh2m;
    private Double prmsl;
    private Integer aPCPsfc;
    private Integer wndspd10m;
    private Integer wndgust10m;
    private Integer wnddir10m;
    private String wnddircard10;
    private Integer issnow;
    private String hcdc;
    private String mcdc;
    private String lcdc;
    private Integer hgt0c;
    private Integer kindex;
    private String cape1800;
    private Integer cin1800;

    private String heure;

    public DonneesHoraires(JSONObject js, String hour){

        try {
            heure=hour;
            icon=js.getString("ICON");
            condition=js.getString("CONDITION");
            conditionKey=js.getString("CONDITION_KEY");
            tmp2m=js.getDouble("TMP2m");
            dpt2m=js.getDouble("DPT2m");
            wndchill2m=js.getDouble("WNDCHILL2m");
            humidex=js.getDouble("HUMIDEX");
            rh2m=js.getInt("RH2m");
            prmsl=js.getDouble("PRMSL");
            aPCPsfc=js.getInt("APCPsfc");
            wndspd10m=js.getInt("WNDSPD10m");
            wndgust10m=js.getInt("WNDGUST10m");
            wnddir10m=js.getInt("WNDDIR10m");
            wnddircard10=js.getString("WNDDIRCARD10");
            issnow=js.getInt("ISSNOW");
            hcdc=js.getString("HCDC");
            mcdc=js.getString("MCDC");
            lcdc=js.getString("LCDC");
            hgt0c=js.getInt("HGT0C");
            kindex=js.getInt("KINDEX");
            cape1800=js.getString("CAPE180_0");
            cin1800=js.getInt("CIN180_0");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String Condition) {
        this.condition = Condition;
    }

    public String getConditionKey() {
        return conditionKey;
    }

    public void setConditionKey(String ConditionKey) {
        this.conditionKey = ConditionKey;
    }

    public Double getTMP2m() {
        return tmp2m;
    }

    public void setTMP2m(Double tMP2m) {
        this.tmp2m = tMP2m;
    }

    public Double getDpt2m() {
        return dpt2m;
    }

    public void setDpt2m(Double dpt2m) {
        this.dpt2m = dpt2m;
    }

    public Double getwndchill2m() {
        return wndchill2m;
    }

    public void setwndchill2m(Double wndchill2m) {
        this.wndchill2m = wndchill2m;
    }

    public Double gethumidex() {
        return humidex;
    }

    public void sethumidex(Double humidex) {
        this.humidex = humidex;
    }

    public Integer getRh2m() {
        return rh2m;
    }

    public void setRh2m(Integer rh2m) {
        this.rh2m = rh2m;
    }

    public Double getPrmsl() {
        return prmsl;
    }

    public void setPrmsl(Double prmsl) {
        this.prmsl = prmsl;
    }

    public Integer getAPCPsfc() {
        return aPCPsfc;
    }

    public void setAPCPsfc(Integer aPCPsfc) {
        this.aPCPsfc = aPCPsfc;
    }

    public Integer getwndspd10m() {
        return wndspd10m;
    }

    public void setwndspd10m(Integer wndspd10m) {
        this.wndspd10m = wndspd10m;
    }

    public Integer getwndgust10m() {
        return wndgust10m;
    }

    public void setwndgust10m(Integer wndgust10m) {
        this.wndgust10m = wndgust10m;
    }

    public Integer getwnddir10m() {
        return wnddir10m;
    }

    public void setwnddir10m(Integer wnddir10m) {
        this.wnddir10m = wnddir10m;
    }

    public String getwnddircard10() {
        return wnddircard10;
    }

    public void setwnddircard10(String wnddircard10) {
        this.wnddircard10 = wnddircard10;
    }

    public Integer getIssnow() {
        return issnow;
    }

    public void setIssnow(Integer issnow) {
        this.issnow = issnow;
    }

    public String gethcdc() {
        return hcdc;
    }

    public void sethcdc(String hcdc) {
        this.hcdc = hcdc;
    }

    public String getMcdc() {
        return mcdc;
    }

    public void setMcdc(String mcdc) {
        this.mcdc = mcdc;
    }

    public String getLcdc() {
        return lcdc;
    }

    public void setLcdc(String lcdc) {
        this.lcdc = lcdc;
    }

    public Integer gethgt0c() {
        return hgt0c;
    }

    public void sethgt0c(Integer hgt0c) {
        this.hgt0c = hgt0c;
    }

    public Integer getKindex() {
        return kindex;
    }

    public void setKindex(Integer kindex) {
        this.kindex = kindex;
    }

    public String getCAPE1800() {
        return cape1800;
    }

    public void setCAPE1800(String cAPE1800) {
        this.cape1800 = cAPE1800;
    }

    public Integer getCIN1800() {
        return cin1800;
    }

    public void setCIN1800(Integer cIN1800) {
        this.cin1800 = cIN1800;
    }


    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
