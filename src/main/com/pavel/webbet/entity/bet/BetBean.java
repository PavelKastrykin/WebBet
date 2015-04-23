package com.pavel.webbet.entity.bet;

public class BetBean {
    private int betId;
    private String login;
    private int footballMatchId;
    private BetPrediction prediction;
    private boolean moneyCharge;
    private double sum;
    private float currentCoef;
    private boolean isWon;
    private BetStatus status;

    public int getBetId() {
        return betId;
    }
    public String getLogin() {
        return login;
    }
    public int getFootballMatchId() {
        return footballMatchId;
    }
    public BetPrediction getPrediction() {
        return prediction;
    }
    public boolean isMoneyCharge() {
        return moneyCharge;
    }
    public double getSum() {
        return sum;
    }
    public float getCurrentCoef() {
        return currentCoef;
    }
    public boolean isWon() {
        return isWon;
    }
    public BetStatus getStatus() {
        return status;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setFootballMatchId(int footballMatchId) {
        this.footballMatchId = footballMatchId;
    }
    public void setPrediction(BetPrediction prediction) {
        this.prediction = prediction;
    }
    public void setMoneyCharge(boolean moneyCharge) {
        this.moneyCharge = moneyCharge;
    }
    public void setSum(double sum) {
        this.sum = sum;
    }
    public void setCurrentCoef(float currentCoef) {
        this.currentCoef = currentCoef;
    }
    public void setWon(boolean isWon) {
        this.isWon = isWon;
    }
    public void setStatus(BetStatus status) {
        this.status = status;
    }
}
