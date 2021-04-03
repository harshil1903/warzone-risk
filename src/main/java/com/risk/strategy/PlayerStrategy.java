package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.List;

/**
 * The abstract claas Player strategy defines various methods for all the strategies to implement
 *
 * @author Harshil
 */
public abstract class PlayerStrategy {


    Player d_player;

    List<Country> d_country;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player the p player
     * @param p_map    the p map
     */
    PlayerStrategy(Player p_player, List<Country> p_map){
        d_player = p_player;
        d_country = p_map;
    }

    /**
     * Create order.
     *
     * @return the order
     */
    public abstract Order createOrder();
}