package DAO;

import DAO.Entities.Projet;
import DAO.Entities.Tache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IProjet implements DAOProjet{
    @Override
    public List<Projet> findall() {

        Connection connection =  SingletonConnectionDB.getConnection();
        List<Projet> projets =new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT `id`, `nom` FROM `projet`");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId(rs.getInt(1));
                projet.setNom(rs.getString(2));
                projets.add(projet);
            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return projets;
    }

    @Override
    public Projet finById(int id) {

        Connection connection =  SingletonConnectionDB.getConnection();
        Projet projet = new Projet();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT `id`, `nom` FROM `projet` Where id = "+id+"");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                projet.setId(rs.getInt(1));
                projet.setNom(rs.getString(2));
            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return projet;
    }

    @Override
    public Projet save(Projet o) {

        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `projet`" +
                    "(`id`, `nom`) VALUES"+
                    "VALUES (?,?);\n");
            pstm.setInt(1, o.getId());
            pstm.setString(2, o.getNom());
            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean detete(Projet o) {

        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `projet` WHERE id = "+o.getId()+"\n");
            pstm.executeUpdate();
        }
        catch (Exception exception){
            return false;
        }
        return true ;
    }

    @Override
    public Projet update(Projet o) {

        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE `projet` SET" +
                    " `id`=?," +
                    "`nom`=?" +
                    " WHERE id= "+o.getId()+"");
            pstm.setInt(1, o.getId());
            pstm.setString(2, o.getNom());
            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;
    }
}
