package DAO.Entities;

import java.io.Serializable;

public class Mission implements Serializable {
    private int id ;
    private String  descreption ;
    private Tache  tache ;
    private Utilisateur utilisateur;

    public Mission(int id, String descreption, Tache tache, Utilisateur utilisateur) {
        this.id = id;
        this.descreption = descreption;
        this.tache = tache;
        this.utilisateur = utilisateur;
    }
    public Mission(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
