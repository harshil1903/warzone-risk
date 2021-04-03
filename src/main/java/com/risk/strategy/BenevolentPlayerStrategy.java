package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Advance;
import com.risk.orders.Deploy;
import com.risk.orders.Order;

import java.util.List;
import java.util.Random;

public class BenevolentPlayerStrategy extends PlayerStrategy {

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  Player object
     * @param p_country List of country
     */
    BenevolentPlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Decide the country to attack to
     * Benevolent Player doesn't attack so this method return null
     *
     * @return null
     */

    protected Country toAttack() {
        return null;
    }

    /**
     * Decide which country should be defend
     * Benevolent Player decide to defend its country with the most armies
     *
     * @return current player country with most number of armies
     */
    protected Country toDefend() {
        Country l_myMinArmies = d_player.getD_AssignedCountries().get(0);
        for (Country l_tempCountry : d_country) {
            if (l_myMinArmies.getD_NumberOfArmies() > l_tempCountry.getD_NumberOfArmies() & d_player.getD_AssignedCountries().contains(l_tempCountry)) {
                l_myMinArmies = l_tempCountry;
            }
        }
        return l_myMinArmies;
    }

    /**
     * Decide where to launch an attack from. The Defensive player does not attack, so it returns null
     *
     * @return null
     */
    protected Country toAttackFrom() {
        return null;
    }

    /**
     * Decide where armies are moved to.
     *
     * @return null
     */
    protected Country toMoveFrom() {
        Country l_myMaxArmies = d_player.getD_AssignedCountries().get(0);
        for (Country l_tempCountry : d_country) {
            if (l_myMaxArmies.getD_NumberOfArmies() < l_tempCountry.getD_NumberOfArmies() && d_player.getD_AssignedCountries().contains(l_tempCountry)) {
                l_myMaxArmies = l_tempCountry;
            }
        }
        return l_myMaxArmies;
    }

    /**
     * Create an order benevolent player can use only deploy order
     *
     * @return created order
     */
    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rndOrder = l_rand.nextInt(4);
        int l_numOfArmies;
        if (l_rand.nextInt(5) != 0) {
            switch (l_rndOrder) {
                case (0):
                    // Deploy Order
                    l_numOfArmies = l_rand.nextInt(20);
                    return new Deploy(d_player, toDefend().getD_CountryName(), l_numOfArmies);
                case (1):
                    // Advance Order
                    l_numOfArmies = l_rand.nextInt(toMoveFrom().getD_NumberOfArmies());
                    return new Advance(d_player, toMoveFrom().getD_CountryName(), toDefend().getD_CountryName(), l_numOfArmies);
                case (2):
                    // AirLift Card
                    if (d_player.getD_cardList().contains("airlift")) {
                        l_numOfArmies = l_rand.nextInt(toMoveFrom().getD_NumberOfArmies());
                        return new Advance(d_player, toMoveFrom().getD_CountryName(), toDefend().getD_CountryName(), l_numOfArmies);
                    }
                case (4):
                    //Blockade Card
                    if (d_player.getD_cardList().contains("blockade")) {
                        l_numOfArmies = l_rand.nextInt(toMoveFrom().getD_NumberOfArmies());
                        return new Advance(d_player, toMoveFrom().getD_CountryName(), toDefend().getD_CountryName(), l_numOfArmies);
                    }
            }
        }
        return null;
    }
}