package org.zygotekillers.hackaton.models;

import java.io.Serializable;

/**
 * Created by djamiirr on 03/12/17.
 */

public class Team  implements Serializable {

    private String teamName, salle, table;

    public Team(){

    }

    public Team(String teamName, String salle, String table) {
        this.teamName = teamName;
        this.salle = salle;
        this.table = table;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
