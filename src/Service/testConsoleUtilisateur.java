package Service;

import DAO.Entities.PC;
import DAO.Entities.Utilisateur;
import DAO.IPC;
import DAO.IUtilisateur;
import DAO.SingletonConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class testConsoleUtilisateur {
    public static void main(String[] args) {
        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `utilisateur`");
            pstm.executeUpdate();
        }
        catch (Exception exception){

        }
        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `pc`");
            pstm.executeUpdate();
        }
        catch (Exception exception){

        }
        GestionUtilisateurs gestionUtilisateurs = new IGestionUtilisateurs(new IUtilisateur());
        GestiondesRessources gestiondesRessources = new IGestiondesRessources(new IPC());

        PC pc1 = new PC(1,"hp","sale9", new Utilisateur());

        Utilisateur utilisateur1 = new Utilisateur(1, "lkolli", "saleheddine","saleheddinelkolli@gmail.com","060001267" ,"0000","Responsable");
        pc1.setUtilisateur(utilisateur1);
        utilisateur1.setPC(pc1);
        gestiondesRessources.AjouterPC(pc1);
        gestionUtilisateurs.AjouterUtilisatuer(utilisateur1);
        //
        PC pc2 = new PC(2,"hp","sale9", new Utilisateur());

        Utilisateur utilisateur2 = new Utilisateur(2, "lkolli", "saleheddine","salehedi@gmail.com","060001267" ,"0000","Responsable");
        pc2.setUtilisateur(utilisateur2);
        gestiondesRessources.AjouterPC(pc2);
        utilisateur2.setPC(pc2);
        gestionUtilisateurs.AjouterUtilisatuer(utilisateur2);

        for (Utilisateur utilisateur : gestionUtilisateurs.getListeUtilisateurs()){
            System.out.println(utilisateur.toString());
        }

        //
        System.out.println(gestionUtilisateurs.getUtilisateur(1));
        //
        System.out.println(gestionUtilisateurs.getUtilisateur("saleheddinelkolli@gmail.com"));
        //
        utilisateur1 = new Utilisateur(1, "saleh", "s","nom@gmail.com","060001267" ,"0000","Responsable");
        utilisateur1.setPC(pc1);

        gestionUtilisateurs.ModifierUtilisateur(utilisateur1);
        for (Utilisateur utilisateur : gestionUtilisateurs.getListeUtilisateurs()){
            System.out.println(utilisateur.toString());
        }
        //
        gestionUtilisateurs.SupprimerUtilisateur(utilisateur2);
        for (Utilisateur utilisateur : gestionUtilisateurs.getListeUtilisateurs()){
            System.out.println(utilisateur.toString());
        }



    }
}
