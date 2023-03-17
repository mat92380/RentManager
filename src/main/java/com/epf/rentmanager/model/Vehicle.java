package com.epf.rentmanager.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Vehicle {
    @Autowired
    private int id;
    @Autowired
    private String constructeur;
   // private String modele;
   @Autowired
    private int nb_place;

    public Vehicle(int id, String constructeur, int nb_place) {
        this.id = id;
        this.constructeur = constructeur;
        //this.modele = modele;
        this.nb_place = nb_place;
    }
    public Vehicle(){
        // a modifier surement avec class vehicle dao l41

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

   /* public String getModele() {
        return modele;
    }*/

   /* public void setModele(String modele) {
        this.modele = modele;
    }*/

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", constructeur='" + constructeur + '\'' +
                ", nb_place=" + nb_place +
                '}';
    }
}
