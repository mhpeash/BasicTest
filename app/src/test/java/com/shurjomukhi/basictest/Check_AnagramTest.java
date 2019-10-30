package com.shurjomukhi.basictest;

import org.junit.Test;

import static com.shurjomukhi.basictest.Q1.Check_Anagram.isAnagram;
import static org.junit.Assert.*;

public class Check_AnagramTest {

    @Test
    public void CheckAnagram() {
        Boolean testCase = isAnagram("one", "eon");
        assertEquals(true,testCase);
    }

    @Test
    public void notAnagram()throws Exception {
        Boolean testCase = isAnagram("one", "two");
        assertFalse("false",testCase);
    }
}