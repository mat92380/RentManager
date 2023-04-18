package com.epf.rentmanager.model;

import com.epf.rentmanager.dao.ClientDao;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
@Service

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate naissance;

    public Client(int id, String nom, String prenom, String email, LocalDate naissance) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }
    public Client(String nom, String prenom, String email, LocalDate naissance) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.naissance = naissance;
    }
    public Client(){
        // A modifier peut etre dans clientDao
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }
    public int getAge(){
        LocalDate naissance = this.getNaissance();
        if(naissance!=null){
            return Period.between(naissance,LocalDate.now()).getYears();
        }else{
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", naissance=" + naissance +
                '}';
    }
}
