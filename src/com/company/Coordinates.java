package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Coordinates {

    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("lat")
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
