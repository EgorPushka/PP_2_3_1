package web.test;

import web.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDB {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydbtest?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
        String user = "root";
        String pass = "root";
        List<User> userList = new ArrayList<>();

        System.out.println("Start Connection ...");

        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Connection Successfull...");
            System.out.println(DriverManager.getDriver("jdbc:mysql://localhost:3306/mydbtest")
                    .getClass()
                    .getName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1 ; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Connection fail...");
            e.printStackTrace();
        }
    }
}
