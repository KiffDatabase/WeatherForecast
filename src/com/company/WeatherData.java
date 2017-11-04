package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

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

    public Date getDt() {
        return new Date(dt * 1000L);
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }
}
