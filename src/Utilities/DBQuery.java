/*
 * Program: DBQuery.java
 * Author: Davis Nguyen
 * Description: DBQuery class used to execute SQL statements
 */

package Utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class executes SQL statements.
 *
 * @author Davis Nguyen
 */
public class DBQuery {

    //statement reference
    private static Statement statement;

    /**
     * Creates a SQL statement object.
     *
     * @param conn Connection statement to database.
     * @throws SQLException If a SQL exception occurred.
     */
    public static void  setStatement(Connection conn) throws SQLException {
        statement = conn.createStatement();
    }

    /**
     * Returns a SQL statement object.
     *
     * @return A statement object.
     */
    public static Statement getStatement(){
        return statement;
    }
}
