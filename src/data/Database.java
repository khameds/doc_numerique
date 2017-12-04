package data;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private String DBPath;
    private Connection connection = null;
    private Statement statement = null;

    public Database(String dBPath) {
        DBPath = dBPath;
    }
    
    public void insertIntoMail(String mail, String firstname, String lastname)
    {
	String sql = "INSERT INTO mail(mail,lastname,firstname) VALUES (?,?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    test.setString(1,mail);
	    test.setString(2,lastname);
	    test.setString(3,firstname);
	    System.out.println("Add mail success");
	    
	    

	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
        public void dropTableMail()
    {
	try{
	    statement.execute("DROP TABLE mail;");
	    System.out.println("Create table successfull!!");
	}
	catch(SQLException e)
	{
	    System.out.println("Error : create table");
	    e.printStackTrace();
	}
    }
    
    
    public void createTableMail()
    {
	try{
	statement.execute("CREATE TABLE IF NOT EXISTS mail ( id integer PRIMARY KEY , mail VARCHAR(255) NOT NULL , lastname VARCHAR(255) NOT NULL , firstname VARCHAR(255) NOT NULL);");
	    System.out.println("Create table successfull!!");
	}
	catch(SQLException e)
	{
	    System.out.println("Error : create table");
	    e.printStackTrace();
	}
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