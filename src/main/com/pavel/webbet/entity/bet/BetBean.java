package com.pavel.webbet.entity.bet;

import java.util.Date;

public class BetBean {
    private int betId;
    private String login;
    private int footballMatchId;
    private String footballMatchName;
    private String matchScore;
    private Date footballMatchDate;
    private BetPrediction prediction;
    private boolean moneyCharge;
    private double sum;
    private float currentCoef;
    private boolean won;
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
    public String getMatchScore(){ return matchScore; }
    public String getFootballMatchName() {return footballMatchName; }
    public Date getFootballMatchDate() {return footballMatchDate; }
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
        return won;
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
    public void setMatchScore(String matchScore) {this.matchScore = matchScore; }
    public void setFootballMatchName(String footballMatchName) {this.footballMatchName = footballMatchName; }
    public void setFootballMatchDate(Date date) {this.footballMatchDate = date; }
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
        this.won = isWon;
    }
    public void setStatus(BetStatus status) {
        this.status = status;
    }
}
