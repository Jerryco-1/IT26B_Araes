package guisimple;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionDB {

    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/basketball_db",
                "root",
                ""
            );
        } catch (Exception e) {
            System.out.println("Connection Error: " + e.getMessage());
        }

        return conn;
    }
}