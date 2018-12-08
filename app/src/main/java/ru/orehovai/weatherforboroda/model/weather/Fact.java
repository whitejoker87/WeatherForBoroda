package ru.orehovai.weatherforboroda.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fact {

    @SerializedName("temp")
    @Expose
    private Integer temp;
    @SerializedName("feels_like")
    @Expose
    private Integer feelsLike;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("wind_speed")
    @Expose
    private Integer windSpeed;
    @SerializedName("wind_gust")
    @Expose
    private Double windGust;
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    @SerializedName("pressure_mm")
    @Expose
    private Integer pressureMm;
    @SerializedName("pressure_pa")
    @Expose
    private Integer pressurePa;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("uv_index")
    @Expose
    private Integer uvIndex;
    @SerializedName("soil_temp")
    @Expose
    private Integer soilTemp;
    @SerializedName("soil_moisture")
    @Expose
    private Double soilMoisture;
    @SerializedName("daytime")
    @Expose
    private String daytime;
    @SerializedName("polar")
    @Expose
    private Boolean polar;
    @SerializedName("season")
    @Expose
    private String season;
    @SerializedName("obs_time")
    @Expose
    private Integer obsTime;
    @SerializedName("accum_prec")
    @Expose
    private AccumPrec accumPrec;
    @SerializedName("source")
    @Expose
    private String source;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Integer feelsLike) {
        this.feelsLike = feelsLike;
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

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Integer getPressureMm() {
        return pressureMm;
    }

    public void setPressureMm(Integer pressureMm) {
        this.pressureMm = pressureMm;
    }

    public Integer getPressurePa() {
        return pressurePa;
    }

    public void setPressurePa(Integer pressurePa) {
        this.pressurePa = pressurePa;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Integer getSoilTemp() {
        return soilTemp;
    }

    public void setSoilTemp(Integer soilTemp) {
        this.soilTemp = soilTemp;
    }

    public Double getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Double soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    public Boolean getPolar() {
        return polar;
    }

    public void setPolar(Boolean polar) {
        this.polar = polar;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Integer getObsTime() {
        return obsTime;
    }

    public void setObsTime(Integer obsTime) {
        this.obsTime = obsTime;
    }

    public AccumPrec getAccumPrec() {
        return accumPrec;
    }

    public void setAccumPrec(AccumPrec accumPrec) {
        this.accumPrec = accumPrec;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}