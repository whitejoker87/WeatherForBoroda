package ru.orehovai.weatherforboroda.model.Geoposition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaDataProperty {

    @SerializedName("GeocoderResponseMetaData")
    @Expose
    private GeocoderResponseMetaData geocoderResponseMetaData;

    public GeocoderResponseMetaData getGeocoderResponseMetaData() {
        return geocoderResponseMetaData;
    }

    public void setGeocoderResponseMetaData(GeocoderResponseMetaData geocoderResponseMetaData) {
        this.geocoderResponseMetaData = geocoderResponseMetaData;
    }

}
