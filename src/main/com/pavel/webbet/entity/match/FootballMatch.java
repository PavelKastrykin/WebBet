package com.pavel.webbet.entity.match;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class FootballMatch {
    private int matchId;
    private String matchName;
    private Date startTime;
    private String matchScore;
    private float winCoef;
    private float drawCoef;
    private float looseCoef;
    private MatchStatus status;

    public int getMatchId() {
        return matchId;
    }
    public String getMatchName() {
        return matchName;
    }
    public Date getStartTime() {
        return startTime;
    }
    public String getMatchScore() {
        return matchScore;
    }
    public float getWinCoef() {
        return winCoef;
    }
    public float getDrawCoef() {
        return drawCoef;
    }
    public float getLooseCoef() {
        return looseCoef;
    }
    public MatchStatus getStatus() {
        return status;
    }
    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }
    public void setStartTime(Timestamp timestamp) {
        this.startTime = new Date(timestamp.getTime());
    }
    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }
    public void setWinCoef(float winCoef) {
        this.winCoef = winCoef;
    }
    public void setDrawCoef(float drawCoef) {
        this.drawCoef = drawCoef;
    }
    public void setLooseCoef(float looseCoef) {
        this.looseCoef = looseCoef;
    }
    public void setStatus(MatchStatus status) {
        this.status = status;
    }
}
