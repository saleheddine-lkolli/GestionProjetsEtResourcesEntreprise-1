package Service;

import DAO.Entities.Utilisateur;

import java.util.List;

public interface GestionUtilisateurs {
    public List<Utilisateur> getListeUtilisateurs();
    public Utilisateur getUtilisateur(String email);
    public Utilisateur getUtilisateur(int id);
    public void SupprimerUtilisateur(Utilisateur utilisateur);
    public void AjouterUtilisatuer(Utilisateur utilisateur);
    public void ModifierUtilisateur(Utilisateur utilisateur);

}
