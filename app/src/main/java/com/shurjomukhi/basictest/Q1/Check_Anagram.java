package com.shurjomukhi.basictest.Q1;

import java.util.Arrays;

public class Check_Anagram {


    public static boolean isAnagram(String wordOne, String wordTwo) {
        if (wordOne.length() == wordTwo.length()) {
            char[] value1 = wordOne.toCharArray();
            char[] value2 = wordTwo.toCharArray();
            Arrays.sort(value1);
            Arrays.sort(value2);
            String x = new String(value1), y = new String(value2);
            return x.equals(y);
        } else return false;
    }
}
