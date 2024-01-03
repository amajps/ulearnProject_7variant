package org.example;

import lombok.Data;

@Data
public class Player {
    private final String name;
    private final String team;
    private final String position;
    private final double height;
    private final double weight;
    private final double age;
}
