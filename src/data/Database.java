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
    
        public void dropTable()
    {
	try{
	    statement.execute("DROP TABLE IF EXISTS mail;");
	    statement.execute("DROP TABLE IF EXISTS message;");
	    statement.execute("DROP TABLE IF EXISTS authorization;");
	    statement.execute("DROP TABLE IF EXISTS institution;");
	    statement.execute("DROP TABLE IF EXISTS target;");
	    
	}
	catch(SQLException e)
	{
	    System.out.println("Error : DROP TABLE");
	    e.printStackTrace();
	}
    }
    
    
    public void createTableMail()
    {
	try{
	    statement.execute("CREATE TABLE IF NOT EXISTS institution (institutionid INT AUTO_INCREMENT PRIMARY KEY NOT NULL,name VARCHAR(255) NOT NULL);");
	    statement.execute("CREATE TABLE IF NOT EXISTS mail (mailid VARCHAR(255) NOT NULL PRIMARY KEY , lastname VARCHAR(255) NOT NULL , firstname VARCHAR(255) NOT NULL, institutionID INT NOT NULL);");
	    statement.execute("CREATE TABLE IF NOT EXISTS message (messageid INT AUTO_INCREMENT PRIMARY KEY NOT NULL, type VARCHAR(255) NOT NULL,emitterID INT NOT NULL, receiverID INT NOT NULL, emissionDate DATE);");
	    statement.execute("CREATE TABLE IF NOT EXISTS authorization (authorizationid INT AUTO_INCREMENT PRIMARY KEY NOT NULL , authemitterID INT NOT NULL , authreceiverID INT NOT NULL , endDate DATE);");
	    statement.execute("CREATE TABLE IF NOT EXISTS target (messageID INT NOT NULL, mailaddress VARCHAR(255) NOT NULL);");
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