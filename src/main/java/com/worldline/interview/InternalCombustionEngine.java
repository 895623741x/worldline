package com.worldline.interview;

public class InternalCombustionEngine extends Engine {


    public InternalCombustionEngine(FuelType requiredFuelType) {
        super(requiredFuelType);
    }

    @Override
    protected boolean validateFuel(FuelType fuelType) {
        return fuelType == FuelType.PETROL || fuelType == FuelType.DIESEL;
    }
}
