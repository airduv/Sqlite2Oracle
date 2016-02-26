import java.awt.Cursor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        ResultSet rsTables = null;
        ResultSet rsColumns = null;
        Statement stmt1 = null;
        Statement stmt2 = null;
        String tabName;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\MM-Copie.DB");
            stmt1 = connection.createStatement();
            // Liste des tables
            rsTables = stmt1.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");
            while (rsTables.next()) {
                tabName = rsTables.getString("name");
                System.out.println("TABLE : " + tabName);
                // Liste des colonnes
                stmt2 = connection.createStatement();
                rsColumns = stmt2.executeQuery("PRAGMA table_info(" + tabName + ")");
                while (rsColumns.next()) {
                    System.out.println(" - col : " + rsColumns.getInt("cid") + " -> " + rsColumns.getString("name") +
                                       (rsColumns.getBoolean("pk") ? " (PK)" : ""));

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rsTables.close();
                rsColumns.close();
                stmt1.close();
                stmt2.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
