package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Helps in Loading the Conquest Format Map file
 *
 * @author Chirag
 */
public class LoadConquestMap {
    public static Map p_map = new Map();

    /**
     * This method gets country from p_Map.
     *
     * @param p_countryName name of the country.
     * @param p_Map         Stores data read from p_mapReader
     * @return the country object for used in GetCountries.
     */
    public static Country getCountry(String p_countryName, Map p_Map) {
        for (Continent l_continent : p_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryName().equals(p_countryName)) {
                    return l_country;
                }
            }
        }
        return null;
    }


    /**
     * This Method reads Continents from map file and add it to p_Map variable.
     *
     * @param p_mapReader Scanner object that helps to read map file.
     * @param p_map       Stores data read from p_mapReader
     * @throws InvalidMapException if map is not valid.
     */
    private static void getConquestContinents(Scanner p_mapReader, Map p_map) {
        int l_continentID = 1;
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split("=");
            String l_continentName = l_parts[0];
            int l_controlValue = Integer.parseInt(l_parts[1]);
            Continent l_continent = new Continent(l_continentID, l_continentName, l_controlValue);
            p_map.addContinentToContinentList(l_continent);
            l_continentID++;
        }
    }

    /**
     * Helps in adding Adjacent Countries to the Map
     *
     * @param p_map         Stores data read from p_mapReader
     * @param l_parts       array of strings consisting adjacent countries
     * @param l_country     Object of country
     * @param l_continentID Continent ID of the continent
     */
    private static void fillConquestAdjacentTerritories(Map p_map, String[] l_parts, Country l_country, int l_continentID) {
        Continent l_continent = p_map.getContinentFromContinentList(l_continentID);
        for (int i = 4; i < l_parts.length; i++) {
            String l_adjacentCountry = l_parts[i];
            Country l_neighbour = getCountry(l_adjacentCountry, p_map);
            l_country.addCountryToAdjacentCountries(l_neighbour);
        }
    }


    /**
     * Helps in getting Adjacent Countries.
     *
     * @param p_mapReader Scanner objects that helps to read the map.
     * @param p_map       Stores data read from p_mapReader
     */
    private static void getConquestAdjacentTerritories(Scanner p_mapReader, Map p_map) {
        int l_continentID = 1;
        int l_countryID = 1;
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) {
                l_continentID++;
                continue;
            }
            String[] l_parts = l_line.split(",");
            Continent l_continent = p_map.getContinentFromContinentList(l_continentID);
            String l_countryName = l_parts[0];
            Country l_country = l_continent.getCountryFromCountryName(l_countryName);
            fillConquestAdjacentTerritories(p_map, l_parts, l_country, l_continentID);
            l_countryID++;
        }
    }

    /**
     * This Method reads Territories from map file and add it to list in Continent.
     *
     * @param p_mapReader Scanner objects that helps to read the map.
     * @param p_map       Stores data read from p_mapReader
     * @throws InvalidMapException if map is not valid.
     */
    private static void getConquestTerritories(Scanner p_mapReader, Map p_map) {
        int l_continentID = 1;
        int l_countryID = 1;
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) {
                l_continentID++;
                continue;
            }
            String[] l_parts = l_line.split(",");
            Continent l_continent = p_map.getContinentFromContinentList(l_continentID);
            String l_countryName = l_parts[0];
            Country l_country = new Country(l_countryID, l_countryName, l_continentID);
            l_country.setD_BelongToContinent(l_continent);
            l_country.setD_ContinentName(l_continent.getD_ContinentName());
            l_continent.addCountryToCountryList(l_country);
            l_countryID++;
        }
    }


    /**
     * This Method Loads the Conquest Map passed to it.
     *
     * @return the Map object with loaded values.
     */
    public static Map loadConquestMap() {
        String l_path = "src/main/resources/";
        String l_fileName = "Asia.map";
        File l_map = new File(l_path + l_fileName);
        Scanner l_mapReader = null;
        try {
            l_mapReader = new Scanner(l_map);
            while (l_mapReader.hasNextLine()) {
                String l_line = l_mapReader.nextLine();
                if (l_line.equals("[Continents]")) {
                    getConquestContinents(l_mapReader, p_map);
                }
                if (l_line.equals("[Territories]")) {
                    getConquestTerritories(l_mapReader, p_map);
                }
            }

            l_mapReader = new Scanner(l_map);
            while (l_mapReader.hasNextLine()) {
                String l_line = l_mapReader.nextLine();
                if (l_line.equals("[Territories]")) {
                    getConquestAdjacentTerritories(l_mapReader, p_map);
                }
            }
            System.out.println("Loaded map successfully form existing conquest file");

        } catch (FileNotFoundException e) {

        }
        return p_map;
    }


}