package com.shurjomukhi.basictest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("isAnagram: ",isAnagram("aswe","wsea")+"");
    }

    public boolean isAnagram(String wordOne, String wordTwo) {
        char[] value1 = wordOne.toCharArray();
        char[] value2 = wordTwo.toCharArray();
        Arrays.sort(value1);
        Arrays.sort(value2);
        String x= new String(value1), y = new String(value2);
        return x.equals(y);
    }
}
