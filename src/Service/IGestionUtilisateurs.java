package Service;

import DAO.DAOUtilisateur;
import DAO.Entities.Utilisateur;
import DAO.IUtilisateur;

import java.util.List;

public class IGestionUtilisateurs implements GestionUtilisateurs{
    private DAOUtilisateur daoUtilisateur =new IUtilisateur();


    public IGestionUtilisateurs(DAOUtilisateur daoUtilisateur){
        this.daoUtilisateur = daoUtilisateur;
    }
    @Override
    public List<Utilisateur> getListeUtilisateurs() {
        return daoUtilisateur.findall();
    }

    @Override
    public Utilisateur getUtilisateur(String email) {
        return daoUtilisateur.findbyemail(email);
    }

    @Override
    public Utilisateur getUtilisateur(int id) {

        return daoUtilisateur.finById(id);
    }

    @Override
    public void SupprimerUtilisateur(Utilisateur utilisateur) {
        daoUtilisateur.detete(utilisateur);
    }

    @Override
    public void AjouterUtilisatuer(Utilisateur utilisateur) {
        daoUtilisateur.save(utilisateur);
    }

    @Override
    public void ModifierUtilisateur(Utilisateur utilisateur) {
        daoUtilisateur.update(utilisateur);
    }
}
