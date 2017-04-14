package com.amazecreationz.employeemeter.services;

/**
 * Created by Anand Mohan<moghan.anand@gmail.com> on 14/4/17 9:43 PM.
 */

public class StringService {
    public static String getSentenceCase(String input) {
        String[] strArray = input.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String each : strArray) {
            String cap = each.substring(0, 1).toUpperCase() + each.substring(1);
            builder.append(cap + " ");
        }
        return builder.toString();
    }
}
