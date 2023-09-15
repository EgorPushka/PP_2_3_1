package web.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String user = "root";
        String pass = "root";
        System.out.println("Start Connection ...");

        try (Connection connection = DriverManager.getConnection(url,user,pass)){

            System.out.println("Connection Successfull...");
            System.out.println(DriverManager.getDriver("jdbc:mysql://localhost:3306/mydbtest"));

        } catch (SQLException e) {
            System.out.println("Connection fail...");
            e.printStackTrace();
        }
    }
}
