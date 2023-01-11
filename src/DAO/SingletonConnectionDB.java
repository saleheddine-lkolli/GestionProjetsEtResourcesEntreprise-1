package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnectionDB {
    private  static  Connection connection ;
    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestiondesprojetsressources","root","");
            System.out.println("Connected to Database.");
        }
        catch (Exception exception){
            System.out.println("ERROR: Unable to Connect to Database.");
            exception.getStackTrace();
        }
    }

    public  static Connection getConnection()  {
        return connection;
    }


}
