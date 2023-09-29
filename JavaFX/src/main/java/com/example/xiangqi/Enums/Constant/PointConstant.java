package com.example.xiangqi.Enums.Constant;

import java.util.HashMap;
import java.util.Map;

public class PointConstant {
    // Total point
    public static Double BLACK = 0.0;
    public static Double RED = 0.0;

    // Piece point
    public static Map<String, Double> PointConstant = new HashMap<>();
    static {
        PointConstant.put("Canon", 4.5);
        PointConstant.put("Horse", 4.0);
        PointConstant.put("Advisor", 2.0);
        PointConstant.put("Elephant", 2.0);
        PointConstant.put("Chariot", 9.0);
        PointConstant.put("Soldier", 1.0);
        PointConstant.put("General", 1000.0);
    }

}
