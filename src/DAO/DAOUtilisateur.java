package DAO;

import DAO.Entities.Utilisateur;

public interface DAOUtilisateur extends  DAO<Utilisateur>{
    public Utilisateur findbyemail(String email);
}
