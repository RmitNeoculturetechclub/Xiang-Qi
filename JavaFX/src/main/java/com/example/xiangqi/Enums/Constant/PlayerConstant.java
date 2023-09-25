package com.example.xiangqi.Enums.Constant;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class PlayerConstant {
    public static Map<String, Color> PlayerConstant = new HashMap<>();

    static {
        PlayerConstant.put("Red", Color.RED);
        PlayerConstant.put("Black", Color.BLACK);
    }
}
