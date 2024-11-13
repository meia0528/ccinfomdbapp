package databaseConnection;

import java.sql.*;

public class SQLConnection {
	
	// make sure you have installed the https://dev.mysql.com/downloads/connector/j/ and build path -> add external jar
	// you may look at other sources like stackoverflow and yt if there's an error -> make sure that you have "Connection successful!" when 
	// you run the file

    public static Connection getConnection() {
        Connection conn = null;

        try {
    
            String URL = "jdbc:mysql://localhost:3306/dbconveniencestore";
            String USERNAME = "root"; 
            String PASSWORD = "**"; // Modify to your own password

            // Establish the connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            if (conn != null) {
                System.out.println("Connection successful!");
            }

        } catch (SQLException ex) {
            System.out.println("Connection failed!");
            System.out.println(ex.getMessage());
        }

        return conn; 
    }

    public static void main(String[] args) {
        Connection conn = SQLConnection.getConnection();
        
        if (conn != null) {
            try {
                // Close the connection after test
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
