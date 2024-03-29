package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.risk.main.Main.d_Log;

/**
 * Helps in writing Map data back to conquest map file format
 *
 * @author Chirag
 */
public class ConquestMapWriter {

    /**
     * This method processes the map by calling three different methods and makes a string to be written in map file.
     *
     * @param p_map object of the map which is processed
     * @return String to be written in the map file
     */
    private static String parseConquestMapAndReturnString(Map p_map) {

        StringBuilder l_content = new StringBuilder();
        l_content.append(processConquestContinent(p_map));
        l_content.append(processTerritories(p_map));
        return l_content.toString();
    }

    /**
     * This method processes the continents for Conquest.
     *
     * @param p_map object of the map which is being processed
     * @return a string that contains details of the continents that will eventually be written in the map file.
     */
    private static StringBuilder processConquestContinent(Map p_map) {
        StringBuilder l_continentData = new StringBuilder();
        l_continentData.append("[Continents]");
        l_continentData.append("\n");
        for (Continent l_continent : p_map.getD_Continents()) {
            l_continentData.append(l_continent.getD_ContinentName() + "=" + l_continent.getD_ContinentValue());
            l_continentData.append("\n");
        }
        return l_continentData;
    }

    /**
     * This method is for processing Territories.
     *
     * @param p_map object of the map that is being processed
     * @return a string that contains details of countries that will ultimately be written in the map file.
     */
    private static StringBuilder processTerritories(Map p_map) {
        StringBuilder l_countryData = new StringBuilder();
        l_countryData.append("\n");
        l_countryData.append("[Territories]");
        l_countryData.append("\n");

        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                l_countryData.append(l_country.getD_CountryName() + "," + l_continent.getD_ContinentName());

                for (Country l_adjacentCountries : l_country.getD_AdjacentCountries()) {
                    l_countryData.append(",");
                    l_countryData.append(l_adjacentCountries.getD_CountryName());
                }
                l_countryData.append("\n");
            }
        }
        return l_countryData;

    }

    /**
     * This method writes the map details to map file
     *
     * @param p_map  object of the map which is being processed.
     * @param p_file object of file to store map data.
     */
    public static void writeConquestMapFile(Map p_map, File p_file) {

        FileWriter l_fileWriter;
        try {
            if (p_map == null) {
                System.out.println("Map object is NULL! ");
                d_Log.notify("Map object is NULL! ");

            }

            String l_content = parseConquestMapAndReturnString(p_map);
            l_fileWriter = new FileWriter(p_file, false);
            l_fileWriter.write(l_content);
            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            d_Log.notify(e.getMessage());

        }
    }
}
