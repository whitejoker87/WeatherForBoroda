package ru.orehovai.weatherforboroda.model;

public class TownCard {

    private String townName;
    private int tempToday;
    private int tempTomorrow;
    private int tempFirstDay;
    private int tempSecondDay;
    private int tempThirdDay;
    private String dateFirstDay;
    private String dateSecondDay;
    private String dateThirdDay;


    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getTempToday() {
        return tempToday;
    }

    public void setTempToday(int tempToday) {
        this.tempToday = tempToday;
    }

    public int getTempTomorrow() {
        return tempTomorrow;
    }

    public void setTempTomorrow(int tempTomorrow) {
        this.tempTomorrow = tempTomorrow;
    }

    public int getTempFirstDay() {
        return tempFirstDay;
    }

    public void setTempFirstDay(int tempFirstDay) {
        this.tempFirstDay = tempFirstDay;
    }

    public int getTempSecondDay() {
        return tempSecondDay;
    }

    public void setTempSecondDay(int tempSecondDay) {
        this.tempSecondDay = tempSecondDay;
    }

    public int getTempThirdDay() {
        return tempThirdDay;
    }

    public void setTempThirdDay(int tempThirdDay) {
        this.tempThirdDay = tempThirdDay;
    }

    public String getDateFirstDay() {
        return dateFirstDay;
    }

    public void setDateFirstDay(String dateFirstDay) {
        this.dateFirstDay = dateFirstDay;
    }

    public String getDateSecondDay() {
        return dateSecondDay;
    }

    public void setDateSecondDay(String dateSecondDay) {
        this.dateSecondDay = dateSecondDay;
    }

    public String getDateThirdDay() {
        return dateThirdDay;
    }

    public void setDateThirdDay(String dateThirdDay) {
        this.dateThirdDay = dateThirdDay;
    }
}
