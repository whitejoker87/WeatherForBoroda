package ru.orehovai.weatherforboroda.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Reader;
import java.util.List;

public class WeatherData {

    @SerializedName("now")
    @Expose
    private Integer now;
    @SerializedName("now_dt")
    @Expose
    private String nowDt;
    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("fact")
    @Expose
    private Fact fact;
    @SerializedName("forecast")
    @Expose
    private List<Forecast> forecast = null;

    public Integer getNow() {
        return now;
    }

    public void setNow(Integer now) {
        this.now = now;
    }

    public String getNowDt() {
        return nowDt;
    }

    public void setNowDt(String nowDt) {
        this.nowDt = nowDt;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    public static WeatherData fromJson(Reader json){
        try{
            return new Gson().fromJson(json, WeatherData.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}