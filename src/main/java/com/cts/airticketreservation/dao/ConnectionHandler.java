package com.cts.airticketreservation.dao;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHandler {
	public static Connection getConnection() {
		Connection con = null;
	    try
	    {
	    	Properties prop = new Properties();
	    	prop.load(new FileReader("src/main/resources/connection.properties"));
	    	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/airticket",prop);
	    	if(con!=null) 
	    	{
	    		System.out.println("connected to database");   		
	    	}
	    }
	    catch (SQLException e) 
	    {
     		e.printStackTrace();
		} catch (IOException e) 
	    {
			System.out.println("Connection Properties file not found");
			e.printStackTrace();
		}
	    return con;
	}
}