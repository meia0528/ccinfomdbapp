package databaseConnection;

import java.sql.*;

// DO NOT EDIT THIS! only edit the PASSWORD to whatever your password is for workbench
// make sure you have installed the https://dev.mysql.com/downloads/connector/j/ and build path -> add external jar
// you may look at other sources like stackoverflow and yt if there's an error -> make sure that you have "Connection successful!" when 
// you run the file
// you may remove the comment indicator in main to see if this file works first on your end

public class SQLConnection {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            // Database connection details
            String URL = "jdbc:mysql://localhost:3306/dbconveniencestore";
            String USERNAME = "root";
            String PASSWORD = "***"; // Modify to your own password

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

    //public static void main(String[] args) {
        // Test the connection
    //    getConnection();
    //}
}
