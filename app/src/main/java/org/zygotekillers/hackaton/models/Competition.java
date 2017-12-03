package org.zygotekillers.hackaton.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by djamiirr on 03/12/17.
 */

public class Competition implements Serializable {

    private ArrayList<Coach> coaches;
    private ArrayList<Team> teams;
    private String compName;


    public Competition (String compName){
        this.compName = compName;
    }

    public Competition(ArrayList<Coach> coaches, ArrayList<Team> teams, String compName) {
        this.coaches = coaches;
        this.teams = teams;
        this.compName = compName;
    }

    public ArrayList<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(ArrayList<Coach> coaches) {
        this.coaches = coaches;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }
}
