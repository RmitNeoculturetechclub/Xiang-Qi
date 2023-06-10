package com.example.xiangqi.Handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringConversion {
    public StringConversion() {
    }

    public static String titleCase(String text) {

        if (text == null)
            return null;

        Pattern pattern = Pattern.compile("\\b([a-zÀ-ÖØ-öø-ÿ])([\\w]*)");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        StringBuilder buffer = new StringBuilder();

        while (matcher.find())
            matcher.appendReplacement(buffer, matcher.group(1).toUpperCase() + matcher.group(2));

        return matcher.appendTail(buffer).toString();
    }
}
