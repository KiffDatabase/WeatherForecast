package com.company;

public class CityResponse {
    private CityData cities;

    public CityData getCities ()
    {
        return cities;
    }

    public void setCities (CityData cities)
    {
        this.cities = cities;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cities = "+cities+"]";
    }
}
