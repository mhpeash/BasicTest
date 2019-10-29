package com.shurjomukhi.basictest.Q2.B;

public class NewPatternPlane implements IPlane{

    private String Year,type;
    private int floorSize,noPerson;

    @Override
    public String YearOfManufacture(String year) { return this.Year=year; }

    @Override
    public int GetFloorSize(int floorSize) {
        return this.floorSize=floorSize;
    }

    @Override
    public int GetPersonPerFloor(int noPerson) {
        return this.noPerson=noPerson;
    }


    @Override
    public String VehicleType(String vType) {
        return this.type=vType;
    }

    @Override
    public int GetPassenger() {
        return floorSize*noPerson;
    }
}
