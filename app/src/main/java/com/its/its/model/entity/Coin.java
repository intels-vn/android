package com.its.its.model.entity;

/**
 * Created by BiLac on 3/27/2017.
 */

public class Coin {
    private int current_coin;

    public Coin() {
    }

    public Coin(int current_coin) {
        this.current_coin = current_coin;
    }

    public int getCurrent_coin() {
        return current_coin;
    }

    public void setCurrent_coin(int current_coin) {
        this.current_coin = current_coin;
    }
}
