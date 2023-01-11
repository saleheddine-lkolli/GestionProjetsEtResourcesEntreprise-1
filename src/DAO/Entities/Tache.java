package DAO.Entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Tache implements Serializable {
    private int id ;
    private String nom ;
    private Date dateDebut ;
    private Date dateFin ;
    private List<Mission> missions;
    private Projet projet;

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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Tache(int id, String nom, Date dateDebut, Date dateFin, List<Mission> missions, Projet projet) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.missions = missions;
        this.projet = projet;
    }
    public  Tache(){

    }
}
