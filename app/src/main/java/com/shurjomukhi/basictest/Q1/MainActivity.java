package com.shurjomukhi.basictest.Q1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shurjomukhi.basictest.Q2.A.Car;
import com.shurjomukhi.basictest.Q2.A.Plane;
import com.shurjomukhi.basictest.Q2.A.Vehicle;
import com.shurjomukhi.basictest.Q2.B.ICar;
import com.shurjomukhi.basictest.Q2.B.IPlane;
import com.shurjomukhi.basictest.Q2.B.NewPatternCar;
import com.shurjomukhi.basictest.Q2.B.NewPatternPlane;
import com.shurjomukhi.basictest.R;

import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.AppLogger;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.demoPassengers;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.demoWheels;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_Anagram;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_CarModel;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_GetPassenger;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_IsCarExpired;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_NoPassenger;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_Passenger;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_Wheels;
import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.title_hasGas;
import static com.shurjomukhi.basictest.Q1.Check_Anagram.isAnagram;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        /**
        * Q-1: isAnagram
        */

        AppLogger(title_Anagram, isAnagram("bongo", "gonbo") + "");
        AppLogger(title_Anagram, isAnagram("bongo", "bd") + "");

        /**
         *  Q-2A: Design Pattern
         * Pattern A for Car model
         */
        Vehicle aVehicle=new Car();
        Car aCar = new Car();
        aCar.setGas(0);

        AppLogger(title_Passenger,  aVehicle.set_num_of_passengers(demoPassengers)+"");
        AppLogger(title_Wheels, aVehicle.set_num_of_wheels(demoWheels) + "");
        AppLogger(title_hasGas, aVehicle.has_gas() + "");

        /**
         * Pattern A for Plane model
         */
        Vehicle bVehicle=new Plane();
        Plane aPlane=new Plane();
        aPlane.setGas(12);

        AppLogger(title_Passenger, bVehicle.set_num_of_passengers(demoPassengers) + "");
        AppLogger(title_Wheels, bVehicle.set_num_of_wheels(demoWheels) + "");
        AppLogger(title_hasGas, bVehicle.has_gas() + "");

        /**
         *   Q-2B: New Design Pattern
         * Pattern B for Plane model
         */
        IPlane iPlane=new NewPatternPlane();
        iPlane.YearOfManufacture("2019");
        iPlane.VehicleType("Air");
        iPlane.GetFloorSize(4);
        iPlane.GetPersonPerFloor(100);

        AppLogger(title_GetPassenger,  iPlane.GetPassenger() + "");

        /**
         *   Q-2B: New Design Pattern
         * Pattern B for Car model
         */
        ICar iCar=new NewPatternCar(1,5);
        AppLogger(title_CarModel,iCar.carModel("1990") + "");
        AppLogger(title_IsCarExpired,iCar.isExpired() + "");
        AppLogger(title_NoPassenger,iCar.GetPassenger() + "");
    }

}
