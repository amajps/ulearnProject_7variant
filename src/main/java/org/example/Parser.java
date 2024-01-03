package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {
    public static ArrayList<Player> parseCsv(String path){
        ArrayList<Player> schools = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            reader.readLine();
            while (reader.ready()) {
                String[] info = reader.readLine().split(",");
                String name = info[0].replace('\'', ' ');
                String team = info[1].replace('\"', ' ').trim();
                String position = info[2].replace('\"', ' ').trim();;
                double height = Double.parseDouble(info[3]);
                double weight = Double.parseDouble(info[4]);
                double age = Double.parseDouble(info[5]);
                schools.add(new Player(name, team, position, height, weight, age));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return schools;
    }
}
