package com.animsh.newsdeap.data;

/**
 * Created by animsh on 1/21/2021.
 */
public class Country {
    private String country;
    private String abbreviation;

    public Country() {
    }

    public Country(String country, String abbreviation) {
        this.country = country;
        this.abbreviation = abbreviation;
    }

    public String getCountry() {
        return country;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getCountryFlag() {
        return "https://www.countryflags.io/" + abbreviation.toLowerCase() + "/flat/64.png";
    }
}
