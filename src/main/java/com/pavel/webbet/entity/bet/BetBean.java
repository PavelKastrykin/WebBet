package com.pavel.webbet.entity.bet;

import com.pavel.webbet.entity.match.FootballMatch;
import com.pavel.webbet.entity.userbean.UserBean;

public class BetBean {

    private FootballMatch match = new FootballMatch();
    private UserBean user = new UserBean();

    private int betId;
    private BetPrediction prediction;
    private boolean moneyCharge;
    private int sum;
    private float currentCoef;
    private boolean won;
    private BetStatus status;

    public UserBean getUser(){ return user; }
    public FootballMatch getMatch(){ return match; }
    public int getBetId() {
        return betId;
    }
    public BetPrediction getPrediction() {
        return prediction;
    }
    public boolean isMoneyCharge() {
        return moneyCharge;
    }
    public int getSum() {
        return sum;
    }
    public float getCurrentCoef() {
        return currentCoef;
    }
    public boolean isWon() {
        return won;
    }
    public BetStatus getStatus() {
        return status;
    }

    public void setMatch(FootballMatch match){ this.match = match; }
    public void setUser(UserBean user){ this.user = user; }
    public void setBetId(int betId) {
        this.betId = betId;
    }
    public void setPrediction(BetPrediction prediction) {
        this.prediction = prediction;
    }
    public void setMoneyCharge(boolean moneyCharge) {
        this.moneyCharge = moneyCharge;
    }
    public void setSum(int sum) {
        this.sum = sum;
    }
    public void setCurrentCoef(float currentCoef) {
        this.currentCoef = currentCoef;
    }
    public void setWon(boolean isWon) {
        this.won = isWon;
    }
    public void setStatus(BetStatus status) {
        this.status = status;
    }
}
