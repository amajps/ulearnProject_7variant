package org.example;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = Parser.parseCsv("src/main/resources/sports.csv");
        try {
            Database.connectDB();
            Database.createTable();
//            Database.addDataToTable(players);
            Database.firstTask();
            Database.secondTask();
            Database.thirdTask();
            Database.closeDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}