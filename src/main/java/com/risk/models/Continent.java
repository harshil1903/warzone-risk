package com.risk.models;

import java.util.ArrayList;
import java.util.List;


/**
 * This class defines Continent and its properties such as
 * Name, control value, list of Countries it has
 *
 * @author Harshil
 */
public class Continent {
    private int d_ContinentID;
    private String d_ContinentName;
    private int d_ContinentValue;
    private List<Country> d_Countries;
    private boolean d_IsVisited = false;
    //private HashMap<String, Country> d_countryMap;

    /**
     * Default Constructor
     */
    public Continent() {
        this.d_Countries = new ArrayList<>();

    }

    /**
     * Instantiates a new Continent.
     *
     * @param p_ContinentName  Name of the continent
     * @param p_ContinentValue control value of the continent
     */
    public Continent(String p_ContinentName, int p_ContinentValue) {
        super();
        d_ContinentName = p_ContinentName;
        d_ContinentValue = p_ContinentValue;
        this.d_Countries = new ArrayList<>();
    }

    /**
     * Gets continent id.
     *
     * @return continent id
     */
    public int getD_ContinentID() {
        return d_ContinentID;
    }

    /**
     * Sets continent id.
     *
     * @param p_ContinentID continent id
     */
    public void setD_ContinentID(int p_ContinentID) {
        d_ContinentID = p_ContinentID;
    }

    /**
     * Gets continent name.
     *
     * @return continent name
     */
    public String getD_ContinentName() {
        return d_ContinentName;
    }

    /**
     * Sets continent name.
     *
     * @param p_ContinentName continent name
     */
    public void setD_ContinentName(String p_ContinentName) {
        d_ContinentName = p_ContinentName;
    }

    /**
     * Gets control value of continent
     *
     * @return continent control value
     */
    public int getD_ContinentValue() {
        return d_ContinentValue;
    }

    /**
     * Sets control value of continent
     *
     * @param p_ContinentValue continent control value
     */
    public void setD_ContinentValue(int p_ContinentValue) {
        d_ContinentValue = p_ContinentValue;
    }

    /**
     * Gets list of countries in the continent.
     *
     * @return countries list
     */
    public List<Country> getD_Countries() {
        return d_Countries;
    }

    /**
     * Sets list of countries to the continent.
     *
     * @param p_Countries countries list
     */
    public void setD_Countries(List<Country> p_Countries) {
        d_Countries = p_Countries;
    }

    /**
     * Checks if continent is visited or not
     *
     * @return whether continent is visited or not
     */
    public boolean isD_IsVisited() {
        return d_IsVisited;
    }

    /**
     * Sets visited value of continent
     *
     * @param p_IsVisited whether continent is visited or not
     */
    public void setD_IsVisited(boolean p_IsVisited) {
        d_IsVisited = p_IsVisited;
    }

    /**
     * Add country to country list
     *
     * @param p_Country country to be added
     */
    public void addCountryToCountryList(Country p_Country)
    {
        d_Countries.add(p_Country);
    }

    /**
     * Remove country from country list.
     *
     * @param p_Country country to be removed
     */
    public void removeCountryFromCountryList(Country p_Country)
    {
        d_Countries.remove(p_Country);
    }

    /**
     * To check if country present in country list of the continent or not.
     *
     * @param p_Country country to be checked
     * @return whether country is present in the country list or not
     */
    public boolean isCountryPresentInCountryList(Country p_Country)
    {
        return d_Countries.contains(p_Country);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object p_Object) {

        if (!(p_Object instanceof Continent)) {
            return false;
        }

        if (p_Object == this) {
            return true;
        }

        Continent l_Continent = (Continent) p_Object;
        return l_Continent.getD_ContinentName().equalsIgnoreCase(d_ContinentName);
    }
}