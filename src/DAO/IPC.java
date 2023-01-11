package DAO;

import DAO.Entities.PC;
import DAO.Entities.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IPC implements DAOPC{
    @Override
    public List<PC> findall() {

        Connection connection =  SingletonConnectionDB.getConnection();
        List<PC> pcs =new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `pc`" );
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PC pc = new PC();
                pc.setNum(rs.getInt(1));
                pc.setNom(rs.getString(2));
                pc.setSale(rs.getString(3));
                pc.setUtilisateur(null);
                pcs.add(pc);

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return pcs;
    }
    //
    public List<PC> findallNomUse() {

        Connection connection =  SingletonConnectionDB.getConnection();
        List<PC> pcs =new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `pc`\n" +
                    "EXCEPT\n" +
                    "SELECT p.`num`, p.`sale`, p.`nom` FROM `pc` p RIGHT JOIN `utilisateur` u ON  p.num = u.num_PC;;" );
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PC pc = new PC();
                pc.setNum(rs.getInt(1));
                pc.setNom(rs.getString(2));
                pc.setSale(rs.getString(3));
                pc.setUtilisateur(null);
                pcs.add(pc);

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return pcs;
    }
    @Override
    public PC finById(int id) {
        Connection connection =  SingletonConnectionDB.getConnection();
       PC pc = new PC();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `pc` where num = "+id+"" );
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                pc.setNum(rs.getInt(1));
                pc.setNom(rs.getString(2));
                pc.setSale(rs.getString(3));
                pc.setUtilisateur(null);

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return pc;
    }

    @Override
    public PC save(PC o) {
        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `pc`" +
                    "(`num`,`nom`, `sale`)"+
                    "VALUES (?,?,?);\n");
            pstm.setInt(1, o.getNum());
            pstm.setString(2, o.getNom());
            pstm.setString(3, o.getSale());

            pstm.executeUpdate();

        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        return o;
    }

    @Override
    public boolean detete(PC o) {
        try {
            Connection connection = SingletonConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM `pc` WHERE = ?;");
            pstm.setInt(1, o.getNum());
            pstm.executeUpdate();
        }
        catch (Exception exception){
            return false;
        }
        return true ;
    }

    @Override
    public PC update(PC o) {
        Connection connection = SingletonConnectionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE `pc` SET" +
                    "`nom`=?," +
                    "`sale`=?," +
                    " WHERE num = '"+o.getNum()+"';");
            pstm.setString(1, o.getNom());
            pstm.setString(2, o.getSale());

            pstm.executeUpdate();
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return o;
    }

    @Override
    public PC findbyName(String name) {
        Connection connection =  SingletonConnectionDB.getConnection();
        PC pc = new PC();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `pc` where nom = '"+name+"'" );
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                pc.setNum(rs.getInt(1));
                pc.setNom(rs.getString(2));
                pc.setSale(rs.getString(3));

            }
        }
        catch (SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
        return pc;
    }
}
