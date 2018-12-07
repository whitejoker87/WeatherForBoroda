package ru.orehovai.weatherforboroda.model.Geoposition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.orehovai.weatherforboroda.model.Geoposition.Response;


public class ResponseGeoposition {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}