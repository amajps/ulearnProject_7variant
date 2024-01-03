package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Connection connection;
    private static Statement statement;

    public static void connectDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        statement = connection.createStatement();
    }

    public static void createTable() throws SQLException {
        statement.execute(
                "CREATE TABLE IF NOT EXISTS Players (" +
                        "name TEXT, " +
                        "team TEXT, " +
                        "position TEXT, " +
                        "height FLOAT, " +
                        "weight FLOAT, " +
                        "age FLOAT);"
        );
    }

    public static void closeDB() throws SQLException {
        connection.close();
        statement.close();
    }


    public static void addDataToTable(ArrayList<Player> players) {
        players.forEach(school -> {
            try {
                statement.execute(String.format(
                        "INSERT INTO Players " +
                                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
                        school.getName(), school.getTeam(), school.getPosition(),
                        school.getHeight(), school.getWeight(), school.getAge())
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public static void firstTask() throws SQLException {
        String firstSql = "SELECT team, AVG(age)" +
                " FROM Players" +
                " GROUP BY team " +
                "ORDER BY AVG(age)";
        ResultSet res = statement.executeQuery(firstSql);
        Map<String, Double> map = new HashMap<>();
        while (res.next()){
            map.put(res.getString(1),
                    Double.valueOf(res.getString(2)));
        }
        Graph graph = new Graph(map);
        graph.setVisible(true);
    }

    public static void secondTask() throws SQLException {
        String secondSql = "SELECT name, team " +
                "FROM Players " +
                "GROUP BY team " +
                "ORDER By AVG(height) DESC " +
                "LIMIT 1";
        String team = statement.executeQuery(secondSql).getString("team");
        System.out.printf("Команда с самым высоким средним ростом: %s\n", team);
        String secondSql_2 = String.format("SELECT name FROM Players " +
                "WHERE (team = '%s') ORDER BY height DESC LIMIT 5\n", team);
        ResultSet res = statement.executeQuery(secondSql_2);
        while (res.next()){
            System.out.println(res.getString("name"));
        }
    }

    public static void thirdTask() throws SQLException {
        String thirdSql = "SELECT team, AVG(height), AVG(weight), AVG(age)" +
                " FROM Players" +
                " GROUP BY team " +
                "HAVING AVG(height) BETWEEN 74 AND 78 AND AVG(weight) BETWEEN 190 AND 210" +
                " ORDER BY AVG(age) DESC LIMIT 1";
        String res = statement.executeQuery(thirdSql).getString("team");
        System.out.printf("Команда, с средним ростом равным от 74 до 78 inches и средним весом от 190 до 210 lbs" +
                ", с самым высоким средним возрастом: %s\n", res);
    }
}
