package com.shurjomukhi.basictest.Q2.A;

import android.util.Log;

public class Car implements Vehicle {

    private int Wheels;
    private int Passengers;
    private int Gas;

    @Override
    public int set_num_of_wheels(int wheel) {
        return this.Wheels = wheel;
    }
    @Override
    public int set_num_of_passengers(int passengers) {
        return this.Passengers = passengers;
    }
    @Override
    public boolean has_gas() {
        return Gas > 0;
    }

    public void setGas(int gas) { Gas = gas; }
}
