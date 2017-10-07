package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
public class WeatherData {

    @JsonProperty("coord")
    private Coordinates coord;
    @JsonProperty("main")
    private Temperatures temperatures;
    @JsonProperty("dt")
    private Integer dt;

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public Temperatures getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(Temperatures temperatures) {
        this.temperatures = temperatures;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
