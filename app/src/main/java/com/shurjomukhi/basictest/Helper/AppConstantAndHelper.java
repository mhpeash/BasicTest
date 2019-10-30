package com.shurjomukhi.basictest.Helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AppConstantAndHelper {

    public final static int demoWheels=10;
    public final static int demoPassengers=51;
    public final static int ExpiredDate =10;
    public final static String title_Passenger="Passenger";
    public final static String title_Wheels="Wheels";
    public final static String title_hasGas="hasGas";
    public final static String title_Anagram="isAnagram";
    public final static String title_GetPassenger="NoPassenger";
    public final static String title_CarModel="YearOfManufacturer";
    public final static String title_IsCarExpired="isExpired";
    public final static String title_NoPassenger="TotalPassenger";

    public static void AppLogger(String resultTitle, String result) {
        Log.d("______________ "+resultTitle,"____________________________________"+"\n\n\n\n\n\n\n\n\n");
        Log.d(resultTitle, result+"\n\n\n\n\n\n\n\n\n");
        Log.d("_________________ ","____________________________________");
    }


    public static boolean IsNetworkAvailable(Context context) {
        ConnectivityManager manager =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetwork = manager.getActiveNetworkInfo();
        return actNetwork != null && actNetwork.isConnectedOrConnecting();
    }
}
