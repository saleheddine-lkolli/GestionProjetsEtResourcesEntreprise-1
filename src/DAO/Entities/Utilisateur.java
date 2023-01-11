package DAO.Entities;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;

public class Utilisateur implements Serializable {
    private  int id ;
    private String nom ;
    private String prenom ;
    private String email ;
    private String telephone ;
    private String motdepasse;
    private Fonction typeUtilisateur ;
    private PC pc;
    private List<Mission> missions;


    public Utilisateur(int id, String nom, String prenom, String email, String telephone, String motdepasse, String  typeUtilisateur, PC pc) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motdepasse = motdepasse;
        Fonction fonction = (typeUtilisateur.equals("Responsable"))?Fonction.Responsable:Fonction.Intervenant;
        this.typeUtilisateur = fonction;
        this.pc = pc;

    }
    public Utilisateur(int id, String nom, String prenom, String email, String telephone, String motdepasse, String  typeUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.motdepasse = motdepasse;
        Fonction fonction = (typeUtilisateur.equals("Responsable"))?Fonction.Responsable:Fonction.Intervenant;
        this.typeUtilisateur = fonction;

    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setEquipement(PC pc) {
        this.pc = pc;
    }

    public PC getEquipement() {
        return pc;
    }

    public Utilisateur() {
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public String  getTypeUtilisateur() {
        if(this.typeUtilisateur == Fonction.Responsable)
            return "Responsable";
        else
            return "Intervenant";
    }


    public void setTypeUtilisateur(String typeUtilisateur) {
        if(typeUtilisateur.equals("Responsable"))
            this.typeUtilisateur = Fonction.Responsable;
        else{
            this.typeUtilisateur = Fonction.Intervenant;
        }

    }

    enum Fonction{
        Responsable ,
        Intervenant ;
    }

    public PC getPC() {
        return pc;
    }

    public void setPC(PC pc) {
                this.pc= pc;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                ", typeUtilisateur=" + typeUtilisateur +
                ", PC=" + pc +
                ", missions=" + missions +
                '}';
    }
}
