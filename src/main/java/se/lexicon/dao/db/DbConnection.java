package se.lexicon.dao.db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnection(){
        Connection connection= null;
        try{
            connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("##### MySQL Connection is established #####");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}