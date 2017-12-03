package data;

import java.sql.*;

public class Database {
    private String DBPath;
    private Connection connection = null;
    private Statement statement = null;

    public Database(String dBPath) {
        DBPath = dBPath;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Successfully connected to " + DBPath + " !");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Error : Database connection failed (ClassNotFoundException)...");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Error : Database connection failed (SQLException)...");
        }
    }

    public ResultSet executeQuery(String query){
        ResultSet rs = null;
        try{
            rs = statement.executeQuery(query);
        }
        catch (SQLException e) {
            System.out.println("Error executeQuery(" + query +")");
            e.printStackTrace();
        }

        return rs;
    }

    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while closing database connection");
            e.printStackTrace();
        }
    }
}