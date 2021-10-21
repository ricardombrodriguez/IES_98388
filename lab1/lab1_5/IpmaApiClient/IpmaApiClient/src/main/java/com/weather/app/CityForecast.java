package com.weather.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * the data point with the forecast for a city in a day
 */
public class CityForecast {

    @SerializedName("idRegiao")
    @Expose
    private int idRegiao;
    @SerializedName("idAreaAviso")
    @Expose
    private String idAreaAviso;
    @SerializedName("idConcelho")
    @Expose
    private int idConcelho;
    @SerializedName("globalIdLocal")
    @Expose
    private int globalIdLocal;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("idDistrito")
    @Expose
    private int idDistrito;
    @SerializedName("local")
    @Expose
    private String local;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("precipitaProb")
    @Expose
    private String precipitaProb;
    @SerializedName("tMin")
    @Expose
    private String tMin;
    @SerializedName("tMax")
    @Expose
    private String tMax;
    @SerializedName("predWindDir")
    @Expose
    private String predWindDir;
    @SerializedName("idWeatherType")
    @Expose
    private Integer idWeatherType;
    @SerializedName("classWindSpeed")
    @Expose
    private Integer classWindSpeed;
    @SerializedName("forecastDate")
    @Expose
    private String forecastDate;

    public String getPrecipitaProb() {
        return precipitaProb;
    }

    public void setPrecipitaProb(String precipitaProb) {
        this.precipitaProb = precipitaProb;
    }

    public String getTMin() {
        return tMin;
    }

    public void setTMin(String tMin) {
        this.tMin = tMin;
    }

    public String getTMax() {
        return tMax;
    }

    public void setTMax(String tMax) {
        this.tMax = tMax;
    }

    public String getPredWindDir() {
        return predWindDir;
    }

    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    public Integer getIdWeatherType() {
        return idWeatherType;
    }

    public void setIdWeatherType(Integer idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    public Integer getClassWindSpeed() {
        return classWindSpeed;
    }

    public void setClassWindSpeed(Integer classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public int idRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getIdAreaAviso() {
        return idAreaAviso;
    }

    public void setIdAreaAviso(String idAreaAviso) {
        this.idAreaAviso = idAreaAviso;
    }

    public int idConcelho() {
        return idConcelho;
    }

    public void setIdConcelho(int idConcelho) {
        this.idConcelho = idConcelho;
    }

    public int getGlobalIdLocal() {
        return globalIdLocal;
    }

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
