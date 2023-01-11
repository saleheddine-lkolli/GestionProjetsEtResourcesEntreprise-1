package DAO;

import DAO.Entities.Mission;
import DAO.Entities.Projet;
import DAO.Entities.Tache;
import DAO.Entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ITache implements DAOTache{
    @Override
    public List<Tache> findall() {

        Connection connection =  SingletonConnectionDB.getConnection();
        List<Tache> taches =new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("Select t.`id`, t.`nom`, t.`dateDebut`, " +
                    "t.`dateFin`, t.`id_Projet`, p.`nom` "+
                    "  FROM `mission` m \n" +
                    "INNER join `projet` p on p.id= t.`id_Projet` ");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tache tache = new Tache();
                tache.setId(rs.getInt(1));
                tache.setNom(rs.getString(2));
                tache.setDateDebut(rs.getDate(3));
                tache.setDateFin(rs.getDate(4));
                Projet projet = new Projet();
                projet.setId(rs.getInt(5));
                projet.setNom(rs.getString(6));
                tache.setProjet(projet);
                taches.add(tache);
            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return taches;
    }

    @Override
    public Tache finById(int id) {

        Connection connection =  SingletonConnectionDB.getConnection();
        Tache tache = new Tache();
        try {
            PreparedStatement stm = connection.prepareStatement("Select t.`id`, t.`nom`, t.`dateDebut`, " +
                    "t.`dateFin`, t.`id_Projet`, p.`nom` "+
                    "  FROM `mission` m \n" +
                    "INNER join `projet` p on p.id= t.`id_Projet`" +
                    "where id="+id+"");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                tache.setId(rs.getInt(1));
                tache.setNom(rs.getString(2));
                tache.setDateDebut(rs.getDate(3));
                tache.setDateFin(rs.getDate(4));
                Projet projet = new Projet();
                projet.setId(rs.getInt(5));
                projet.setNom(rs.getString(6));
                tache.setProjet(projet);

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return tache;
    }

    @Override
    public Tache save(Tache o) {

        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `tache`" +
                    "(`id`, `nom`, `dateDebut`, " +
                    "`dateFin`, `id_Projet`) VALUES"+
                    "VALUES (?,?,?,?);\n");
            pstm.setInt(1, o.getId());
            pstm.setString(2, o.getNom());
            pstm.setDate(3, o.getDateDebut());
            pstm.setDate(4, o.getDateFin());
            pstm.setInt(5,o.getProjet().getId());
            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;

    }

    @Override
    public boolean detete(Tache o) {

        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `tache` WHERE id = "+o.getId()+"\n");
            pstm.executeUpdate();
        }
        catch (Exception exception){
            return false;
        }
        return true ;
    }

    @Override
    public Tache update(Tache o) {

        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE `tache` SET " +
                    "`nom`=?," +
                    "`dateDebut`=?," +
                    "`dateFin`=?," +
                    "`id_Projet`=?" +
                    " WHERE id = "+o.getId()+"");
            pstm.setInt(1, o.getId());
            pstm.setString(2, o.getNom());
            pstm.setDate(3, o.getDateDebut());
            pstm.setDate(4, o.getDateFin());
            pstm.setInt(5,o.getProjet().getId());
            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;
    }
}
