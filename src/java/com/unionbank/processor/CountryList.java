/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unionbank.processor;

/**
 *
 * @author wwakanni
 */
public class CountryList implements java.io.Serializable {

    private String countryCode;
    private String countryName;

    public CountryList(String countryCode, String countryName) {
    this.countryCode = countryCode;
    this.countryName = countryName;
    }

    public CountryList() {
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

   
}
