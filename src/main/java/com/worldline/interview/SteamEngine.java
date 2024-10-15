package com.worldline.interview;

public class SteamEngine extends Engine{

    public SteamEngine(FuelType requiredFuelType) {
        super(requiredFuelType);
    }

    @Override
    protected boolean validateFuel(FuelType fuelType) {
        return fuelType == FuelType.WOOD || fuelType == FuelType.COAL;
    }
}
