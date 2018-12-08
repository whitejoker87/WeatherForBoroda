package ru.orehovai.weatherforboroda.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccumPrec {

    @SerializedName("1")
    @Expose
    private Double _1;
    @SerializedName("3")
    @Expose
    private Double _3;
    @SerializedName("7")
    @Expose
    private Double _7;

    public Double get1() {
        return _1;
    }

    public void set1(Double _1) {
        this._1 = _1;
    }

    public Double get3() {
        return _3;
    }

    public void set3(Double _3) {
        this._3 = _3;
    }

    public Double get7() {
        return _7;
    }

    public void set7(Double _7) {
        this._7 = _7;
    }

}
