package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;
//import com.risk.main.Main.d_NeutralPlayer;
import java.util.ArrayList;

/**
 * The type Blockade
 *
 * @author Parth
 * @author Harsh
 */

public class Blockade implements Order {

    String d_countryName;
    Country d_country;
    Player d_player;

    /**
     * constructor of Blockade class
     *
     * @param p_player      Current player object
     * @param p_countryName Country Id
     */

    public Blockade(Player p_player, String p_countryName) {
        this.d_countryName = p_countryName;
        this.d_player = p_player;
        d_country = new Country();
        d_country = d_country.getCountryFromCountryName(p_countryName);
    }

    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid() {
        //here firstly check if player have a blockade card or not after chirag make cardlist of each player
        if (d_player.getD_cardList().contains("blockade")) {
            if (d_country.getD_NumberOfArmies() > 0) {
                ArrayList<String> l_countriesOwnedList = new ArrayList<>();
                for (Country l_country : d_player.getD_AssignedCountries()) {
                    l_countriesOwnedList.add(l_country.getD_CountryName());
                }
                if (!l_countriesOwnedList.contains(d_countryName)) {
                    System.out.println(d_player.getD_PlayerName() + " can not use Blockade card on opponent’s country");
                    return false;
                }
                return true;
            } else {
                System.out.println(d_countryName + " has a 0 army so you can not apply Blockade order there");
                return false;
            }


        } else {
            System.out.println("Player does not contain blockade card");
            return false;
        }

    }


    /**
     * Execute.
     */
    public void execute() {
        if (valid()) {
            int l_previousArmy = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
            System.out.println("Before Blockade Card number of army in " + d_countryName + " is : " + d_country.getD_NumberOfArmies());
            d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies((l_previousArmy * 3));
            System.out.println(d_player.getD_PlayerName() + " applied Blockade Card successfully");
            System.out.println("After Blockade Card number of army in " + d_countryName + " is : " + d_country.getD_NumberOfArmies());
            d_player.removeCard("blockade");
            d_player.removeCountryFromAssignedCountries(d_country.getD_CountryID());
           // d_NeutralPlayer.addCountryToAssignedCountries(d_country);


        }
    }
}
