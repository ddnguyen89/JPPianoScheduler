/*
 * Program: DBConnection.java
 * Author: Davis Nguyen
 * Description: DBConnection class used to connect to database
 */

package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class connects to database.
 *
 * @author Davis Nguyen
 */
public class DBConnection {

    //jdbc url parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com/WJ05ymj";

    //jdbc url
    private static final String jdbcURL = protocol + vendorName + ipAddress + "?connectionTimeZone=SERVER";

    //driver and connection interface reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    //username
    private static final String username = "U05ymj";
    //password
    private static String password = "53688645853";

    /**
     * Creates and starts a connection to database.
     *
     * @return Connection to database.
     */
    public static Connection startConnection(){
        try {
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("CNFE Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return conn;
    }

    /** Closes connection to database. */
    public static void closeConnection(){
        try {
            conn.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
