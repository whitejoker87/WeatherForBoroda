package ru.orehovai.weatherforboroda.model.Geoposition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressDetails {

    @SerializedName("ru.orehovai.weatherforboroda.model.Geoposition.Country")
    @Expose
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
