package org.zygotekillers.hackaton.models;

import java.io.Serializable;

/**
 * Created by djamiirr on 03/12/17.
 */

public class Coach implements Serializable {

    private String username, domaine, nom, prenom;
    private Competition comp;

    public Coach() {

    }

    public Coach(String username, String domaine, String nom, String prenom) {
        this.username = username;
        this.domaine = domaine;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Competition getComp() {
        return comp;
    }

    public void setComp(Competition comp) {
        this.comp = comp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
