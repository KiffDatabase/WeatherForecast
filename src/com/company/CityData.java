package com.company;

public class CityData
{
    private String[] city;

    public String[] getCity ()
    {
        return city;
    }

    public void setCity (String[] city)
    {
        this.city = city;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [city = "+city+"]";
    }
}