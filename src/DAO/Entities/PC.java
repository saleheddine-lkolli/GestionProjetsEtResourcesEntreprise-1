package DAO.Entities;

import java.io.Serializable;

public class PC implements Serializable {
    private int  num ;
    private String nom;
    private String sale ;
    private  Utilisateur utilisateur;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public PC(int num,  String nom ,String sale, Utilisateur utilisateur) {
        this.num = num;
        this.nom = nom;
        this.sale = sale;
        this.utilisateur = utilisateur;
    }
    public PC(int num, String nom, String sale) {
        this.num = num;
        this.nom = nom;
        this.sale = sale;

    }

    public PC() {

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        utilisateur = utilisateur;
    }

}
