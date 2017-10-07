package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
public class WeatherResponse {

    @JsonProperty("list")
    private List<WeatherData> list;

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }
}
