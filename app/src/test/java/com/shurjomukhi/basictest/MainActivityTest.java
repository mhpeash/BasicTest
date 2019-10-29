package com.shurjomukhi.basictest;

import com.shurjomukhi.basictest.Q1.MainActivity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Test
    public void isAnagram()throws Exception {
        MainActivity acMain = new MainActivity();
        Boolean testCase = acMain.isAnagram("one", "eon");
        assertEquals(true,testCase);
    }
    @Test
    public void notAnagram()throws Exception {
        MainActivity acMain = new MainActivity();
        Boolean testCase = acMain.isAnagram("one", "two");
       assertFalse("false",testCase);
    }
}