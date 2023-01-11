package DAO;

import DAO.Entities.Mission;
import DAO.Entities.PC;
import DAO.Entities.Tache;
import DAO.Entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IMission implements DAOMission{
    @Override
    public List<Mission> findall() {

        Connection connection = SingletonConnectionDB.getConnection();
        List<Mission> missions =new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT m.`id`, m.`descreption`," +
                    " m.`id_Utilisateur`, m.`id_Tache` ," +
                    " t.`nom`, t.`dateDebut`, t.`dateFin` , u.`nom`," +
                    " u.`prenom`, u.`email`, u.`Telephone`, u.`motdepasse`, u.`typeUtilisateur`," +
                    "  FROM `mission` m \n" +
                    "INNER join `tache` t on t.id= m.id_Tache \n" +
                    "\tinner join utilisateur u on u.id = m.id_Utilisateur;" );
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Mission mission = new Mission();
                mission.setId(rs.getInt(1));
                mission.setDescreption(rs.getString(2));
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getInt(3));
                utilisateur.setNom(rs.getString(8));
                utilisateur.setPrenom(rs.getString(9));
                utilisateur.setEmail(rs.getString(10));
                utilisateur.setTelephone(rs.getString(11));
                utilisateur.setMotdepasse(rs.getString(12));
                utilisateur.setTypeUtilisateur(rs.getString(13));
                Tache tache = new Tache();
                tache.setId(rs.getInt(4));
                tache.setNom(rs.getString(5));
                tache.setDateDebut(rs.getDate(6));
                tache.setDateFin(rs.getDate(7));
                mission.setTache(tache);
                mission.setUtilisateur(utilisateur);
                missions.add(mission);

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return missions;
    }

    @Override
    public Mission finById(int id) {

        Connection connection =  SingletonConnectionDB.getConnection();
        Mission mission = new Mission();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT m.`id`, m.`descreption`," +
                    " m.`id_Utilisateur`, m.`id_Tache` ," +
                    " t.`nom`, t.`dateDebut`, t.`dateFin` , u.`nom`," +
                    " u.`prenom`, u.`email`, u.`Telephone`, u.`motdepasse`, u.`typeUtilisateur`," +
                    "  FROM `mission` m \n" +
                    "INNER join `tache` t on t.id= m.id_Tache \n" +
                    "\tinner join utilisateur u on u.id = m.id_Utilisateur where id= "+id+"");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                mission.setId(rs.getInt(1));
                mission.setDescreption(rs.getString(2));
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId(rs.getInt(3));
                utilisateur.setNom(rs.getString(8));
                utilisateur.setPrenom(rs.getString(9));
                utilisateur.setEmail(rs.getString(10));
                utilisateur.setTelephone(rs.getString(11));
                utilisateur.setMotdepasse(rs.getString(12));
                utilisateur.setTypeUtilisateur(rs.getString(13));
                Tache tache = new Tache();
                tache.setId(rs.getInt(4));
                tache.setNom(rs.getString(5));
                tache.setDateDebut(rs.getDate(6));
                tache.setDateFin(rs.getDate(7));
                mission.setTache(tache);
                mission.setUtilisateur(utilisateur);
            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return mission;
    }

    @Override
    public Mission save(Mission o) {

        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO " +
                    "`mission`(`id`," +
                    " `descreption`," +
                    " `id_Utilisateur`," +
                    " `id_Tache`) "+
                    "VALUES (?,?,?,?);\n");
            pstm.setInt(1, o.getId());
            pstm.setString(2, o.getDescreption());
            pstm.setInt(3, o.getUtilisateur().getId());
            pstm.setInt(4, o.getTache().getId());
            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean detete(Mission o) {

        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `mission` WHERE id = "+o.getId()+"\n");
            pstm.executeUpdate();
        }
        catch (Exception exception){
            return false;
        }
        return true ;
    }

    @Override
    public Mission update(Mission o) {
        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE `mission` SET " +
                    "`descreption`=?," +
                    "`id_Utilisateur`=?," +
                    "`id_Tache`=?" +
                    " WHERE id= "+o.getId()+"");
            pstm.setString(1, o.getDescreption());
            pstm.setInt(2, o.getUtilisateur().getId());
            pstm.setInt(3, o.getTache().getId());
            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;

    }
}
