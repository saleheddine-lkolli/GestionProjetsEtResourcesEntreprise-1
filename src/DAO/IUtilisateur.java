package DAO;
import DAO.Entities.PC;
import DAO.Entities.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IUtilisateur implements DAOUtilisateur{

    @Override
    public List<Utilisateur> findall() {
        Connection connection =  SingletonConnectionDB.getConnection();
        List<Utilisateur> utilisateurs=new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT u.`id`, u.`nom`, u.`prenom`, u.`email`," +
                    " u.`Telephone`, u.`motdepasse`, u.`typeUtilisateur`," +
                    " u.`num_PC`,p.`nom`,p.`sale` FROM `utilisateur` u " +
                    "INNER JOIN `pc` p  ON p.num = u.num_PC ;" );
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur =  new Utilisateur();
                utilisateur.setId(rs.getInt(1));
                utilisateur.setNom(rs.getString(2));
                utilisateur.setPrenom(rs.getString(3));
                utilisateur.setEmail(rs.getString(4));
                utilisateur.setTelephone(rs.getString(5));
                utilisateur.setMotdepasse(rs.getString(6));
                utilisateur.setTypeUtilisateur(rs.getString(7));
                PC pc = new PC();
                pc.setNum(rs.getInt(8));
                pc.setNom(rs.getString(9));
                pc.setSale(rs.getString(10));
                utilisateur.setPC(pc);
                utilisateurs.add(utilisateur);

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public Utilisateur finById(int id) {
        Connection connection =  SingletonConnectionDB.getConnection();
        Utilisateur utilisateur = new Utilisateur();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT u.`id`, u.`nom`, u.`prenom`, u.`email`," +
                    " u.`Telephone`, u.`motdepasse`, u.`typeUtilisateur`," +
                    " u.`num_PC`,p.`nom`,p.`sale` FROM `utilisateur` u " +
                    "INNER JOIN `pc` p  ON u.id = "+id+" AND u.num_PC = p.num ;" );
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                utilisateur.setId(rs.getInt(1));
                utilisateur.setNom(rs.getString(2));
                utilisateur.setPrenom(rs.getString(3));
                utilisateur.setEmail(rs.getString(4));
                utilisateur.setTelephone(rs.getString(5));
                utilisateur.setMotdepasse(rs.getString(6));
                utilisateur.setTypeUtilisateur(rs.getString(7));
                PC pc = new PC();
                pc.setNum(rs.getInt(8));
                pc.setNom(rs.getString(9));
                pc.setSale(rs.getString(10));
                utilisateur.setPC(pc);
            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return utilisateur;
    }


    @Override
    public Utilisateur save(Utilisateur o) {
        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `utilisateur`" +
                    "(`id`," +
                            " `nom`" +
                            ", `prenom`," +
                            " `email`, " +
                            "`Telephone`," +
                            " `motdepasse`," +
                            " `typeUtilisateur`," +
                            " `num_PC`) "+
                    "VALUES (?,?,?,?,?,?,?,?);\n");
            pstm.setInt(1, o.getId());
            pstm.setString(2, o.getNom());
            pstm.setString(3, o.getPrenom());
            pstm.setString(4, o.getEmail());
            pstm.setString(5, o.getTelephone());
            pstm.setString(6, o.getMotdepasse());
            pstm.setString(7,  o.getTypeUtilisateur());
            pstm.setInt(8, o.getPC().getNum());

            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean detete(Utilisateur o) {
        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `utilisateur` WHERE id ="+o.getId()+"\n");
            pstm.executeUpdate();
        }
        catch (Exception exception){
            return false;
        }
        return true ;
    }

    @Override
    public Utilisateur update(Utilisateur o) {
        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE `utilisateur` SET " +
                    "`nom`=?," +
                    "`prenom`=?," +
                    "`email`=?," +
                    "`Telephone`=?," +
                    "`motdepasse`=?," +
                    "`typeUtilisateur`=?," +
                    "`num_PC`=?  WHERE `id`="+o.getId()+";");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getPrenom());
            pstm.setString(3, o.getEmail());
            pstm.setString(4, o.getTelephone());
            pstm.setString(5, o.getMotdepasse());
            pstm.setString(6,  o.getTypeUtilisateur());
            pstm.setInt(7, o.getPC().getNum());

            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;

    }

    @Override
    public Utilisateur findbyemail(String email) {
        Connection connection =  SingletonConnectionDB.getConnection();
        Utilisateur utilisateur = new Utilisateur();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT u.`id`, u.`nom`, u.`prenom`, u.`email`, u.`Telephone`, u.`motdepasse`, u.`typeUtilisateur`, u.`num_PC`,p.`nom`,p.`sale` FROM" +
                    " `utilisateur` u INNER JOIN `pc` p ON p.num = u.num_PC AND u.email= '"+email+"'; " );
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                utilisateur.setId(rs.getInt(1));
                utilisateur.setNom(rs.getString(2));
                utilisateur.setPrenom(rs.getString(3));
                utilisateur.setEmail(rs.getString(4));
                utilisateur.setTelephone(rs.getString(5));
                utilisateur.setMotdepasse(rs.getString(6));
                utilisateur.setTypeUtilisateur(rs.getString(7));
                PC pc = new PC();
                pc.setNum(rs.getInt(8));
                pc.setNom(rs.getString(9));
                pc.setSale(rs.getString(10));
                utilisateur.setPC(pc);
            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return utilisateur;
    }
}
