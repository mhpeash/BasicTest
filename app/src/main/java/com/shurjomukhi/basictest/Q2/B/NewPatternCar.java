package com.shurjomukhi.basictest.Q2.B;

import java.util.Calendar;

import static com.shurjomukhi.basictest.Helper.AppConstantAndHelper.ExpiredDate;

public class NewPatternCar implements ICar {

    private String mName, vType, Year;
    private int floorSize, noPerson;

    public NewPatternCar(int floorSize, int noPerson) {

        this.floorSize = floorSize;
        this.noPerson = noPerson;
    }
    @Override
    public String carModel(String mName) {
        return this.mName = mName;
    }
    @Override
    public boolean isExpired() {
        int date = Calendar.getInstance().get(Calendar.YEAR);
        return date-Integer.valueOf(mName) > ExpiredDate;
    }
    @Override
    public String VehicleType(String vType) {
        return this.vType = vType;
    }
    @Override
    public int GetPassenger() {
        return floorSize * noPerson;
    }

}
