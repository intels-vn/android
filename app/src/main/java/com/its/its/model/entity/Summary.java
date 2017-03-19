package com.its.its.model.entity;

import java.io.Serializable;

/**
 * Created by Chinh Bui on 3/19/2017.
 */

public class Summary implements Serializable {
    private int coinAmount;
    private int totalLose;
    private int totalWin;

    public Summary() {
    }

    public Summary(int coinAmount, int totalLose, int totalWin) {

        this.coinAmount = coinAmount;
        this.totalLose = totalLose;
        this.totalWin = totalWin;
    }

    public int getCoinAmount() {

        return coinAmount;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    public int getTotalLose() {
        return totalLose;
    }

    public void setTotalLose(int totalLose) {
        this.totalLose = totalLose;
    }

    public int getTotalWin() {
        return totalWin;
    }

    public void setTotalWin(int totalWin) {
        this.totalWin = totalWin;
    }
}
