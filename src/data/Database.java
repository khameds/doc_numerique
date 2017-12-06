package data;

import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private String DBPath;
    private Connection connection = null;
    private Statement statement = null;

    public Database(String dBPath) {
        DBPath = dBPath;
    }
    
    
    
    public boolean checkAuthorization(int idInstitution,String idAuth,String mail )
    {
	String sql = "SELECT COUNT(authorizationid) FROM authorization WHERE authorizationid='"+ idAuth +"' AND institutionID='"+ idInstitution +"' AND mailID='"+ mail +"';";
	try {
	    ResultSet rs = statement.executeQuery(sql);
	    
	    while(rs.next())
	    {
		int count = rs.getInt("COUNT(authorizationid)");
		if(count == 0)
		    return false;
		else
		    return true;
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return false;
    }
    
    public int GetInstitutionIDFromMail(String mail)
    {
	String sql = "SELECT institutionid FROM institution WHERE mail='"+mail+"';";
	
	try {
	    ResultSet rs = statement.executeQuery(sql);
	    int r = -1;
	    while(rs.next())
	    {
		r = rs.getInt("institutionid");
	    }
	    return r;
	} catch (SQLException ex) {
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
	return -1;
    }
    
    
    public boolean mailExist(String mail)
    {
	String sql = "SELECT COUNT(mailid) FROM mail WHERE mailid='" + mail +"';";
	try {
	    ResultSet rs = statement.executeQuery(sql);
	    while(rs.next())
	    {
		int count = rs.getInt("COUNT(mailid)");
		if(count == 0)
		    return false;
		else
		    return true;
	    }
	} catch (SQLException ex) {
	    System.out.println("MailExist fail");
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return true;
    }
    
    public void insertIntoMail(String mail, String firstname, String lastname)
    {
	String sql = "INSERT INTO mail(mailid,lastname,firstname) VALUES (?,?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    test.setString(1,mail);
	    test.setString(2,lastname);
	    test.setString(3,firstname);
	    test.execute();
	    System.out.println("Add mail success");
	} catch (SQLException ex) {
	    System.out.println("insertIntoMail fail");
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void insertIntoInstitution(String nom, String mail)
    {
	String sql = "INSERT INTO institution(name,mail) VALUES (?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    
	    test.setString(1,nom);
	    test.setString(2,mail);
	    test.execute();
	    System.out.println("Add Institution success");
	} catch (SQLException ex) {
	    System.out.println("insertIntoInstitution fail");
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void insertIntoMessage(String subject,String content,String emissionDate)
    {
	String sql = "INSERT INTO message(subject, content, emissionDate) VALUES (?,?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    test.setString(1,subject);
	    test.setString(2,content);
	    test.setString(3,emissionDate);
	    test.execute();
	    System.out.println("Add message success");
	} catch (SQLException ex) {
	    System.out.println("insertIntoMessage fail");
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void insertIntoAuthorization(String id, String institutionID,String mailID, String endDate)
    {
	String sql = "INSERT INTO authorization(authorizationid,institutionID, mailID,  endDate) VALUES (?,?,?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    test.setString(1,id);
	    test.setString(2,institutionID);
	    test.setString(3,mailID);
	    test.setString(4,endDate);
	    test.execute();
	    System.out.println("Add Auth success");
	} catch (SQLException ex) {
	    System.out.println("insertIntoAuth fail");
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void insertIntoTarget(String messageID,String mailaddress)
    {
	String sql = "INSERT INTO target(messageID, mailaddress) VALUES (?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    test.setString(1,messageID);
	    test.setString(2,mailaddress);
	    test.execute();
	    System.out.println("Add target success");
	} catch (SQLException ex) {
	    System.out.println("insertTarget fail");
	    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void insertIntoExtern(String institutionID,String externauthoID, String endDate)
    {
	String sql = "INSERT INTO externauthorization(institutionID, externauthoID, endDate) VALUES (?,?,?)";
	try {
	    PreparedStatement test = connection.prepareStatement(sql);
	    test.setString(1,institutionID);
	    test.setString(2,externauthoID);
	    test.setString(3,endDate);
	    test.execute();
	    System.out.println("Add extern auth success");
	} catch (SQLException ex) {
	    System.out.println("insertIntoExtern fail");
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
	    statement.execute("DROP TABLE IF EXISTS externauthorization;");
	    
	}
	catch(SQLException e)
	{
	    System.out.println("Error : DROP TABLE");
	}
    }
    
	public void addData()
	{
	    insertIntoMail("mail1@univ.fr","test1","test1");
	    insertIntoMail("mail2@univ.fr","test2","test2");
	    insertIntoMail("mail3@univ.fr","test3","test3");
	    insertIntoMail("mail4@univ.fr","test4","test4");
	    insertIntoMail("mail5@univ.fr","test5","test5");
	    
	    insertIntoMail("institution@univ.fr","NOTRE","INSTITUTION");
	    
	    insertIntoInstitution("UJM","UJM@univ.fr");
	    insertIntoInstitution("ENISE","ENISE@univ.fr");
	    insertIntoInstitution("INFO","INFO@univ.fr");
	    
	    insertIntoMessage("Stage","Un super stage, chez vous!","2018-05-10");
	    insertIntoMessage("Stage","Un super stage, de l'exterieur!","2018-06-09");
	    
	    insertIntoTarget("1","mail2@univ.fr");
	    insertIntoTarget("1","mail1@univ.fr");
	    insertIntoTarget("2","mail3@univ.fr");
	    
	    insertIntoAuthorization("a1","0","mail1@univ.fr","2018-06-09");
	    insertIntoAuthorization("a2","0","mail2@univ.fr","2018-06-09");
	    insertIntoAuthorization("a3","1","mail1@univ.fr","2018-06-09");
	    insertIntoAuthorization("a4","2","mail1@univ.fr","2018-06-09");
	    insertIntoAuthorization("a5","2","mail3@univ.fr","2018-06-09");
    
	}
    
    public void createTableMail()
    {
	try{
	    statement.execute("CREATE TABLE IF NOT EXISTS institution (institutionid INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(255) NOT NULL, mail VARCHAR(255));");
	    statement.execute("CREATE TABLE IF NOT EXISTS mail (mailid VARCHAR(255) NOT NULL PRIMARY KEY , lastname VARCHAR(255) NOT NULL , firstname VARCHAR(255) NOT NULL);");
	    statement.execute("CREATE TABLE IF NOT EXISTS message (messageid INT AUTO_INCREMENT PRIMARY KEY,subject VARCHER(255),content TEXT NOT NULL, emissionDate DATE);");
	    statement.execute("CREATE TABLE IF NOT EXISTS authorization (authorizationid VARCHAR PRIMARY KEY NOT NULL , institutionID INT NOT NULL , mailID VARCHAR(255) NOT NULL , endDate DATE);");
	    statement.execute("CREATE TABLE IF NOT EXISTS target (messageID INT NOT NULL, mailaddress VARCHAR(255) NOT NULL);");
	    statement.execute("CREATE TABLE IF NOT EXISTS externauthorization (institutionID INT NOT NULL, externauthoID VARCHAR NOT NULL, endDate DATE);");
	    
	    
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
            //statement.close();
        } catch (SQLException e) {
            System.out.println("Error while closing database connection");
            e.printStackTrace();
        }
    }
}