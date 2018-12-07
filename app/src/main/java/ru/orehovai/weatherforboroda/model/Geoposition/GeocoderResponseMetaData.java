package ru.orehovai.weatherforboroda.model.Geoposition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocoderResponseMetaData {

    @SerializedName("request")
    @Expose
    private String request;
    @SerializedName("found")
    @Expose
    private String found;
    @SerializedName("results")
    @Expose
    private String results;

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

}
