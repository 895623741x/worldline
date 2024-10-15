package com.worldline.interview;

public abstract class Engine {
    protected boolean running;
    protected int fuelLevel;
    protected FuelType requiredFuelType;
    protected FuelType fuelType;
    protected EngineType engineType;

    public Engine(FuelType requiredFuelType) {
        this.requiredFuelType = requiredFuelType;
        if (requiredFuelType == FuelType.COAL || requiredFuelType == FuelType.WOOD) {
            this.engineType = EngineType.STEAM_ENGINE;
        } else {
            this.engineType = EngineType.INTERNAL_COMBUSTION_ENGINE;
        }
        running = false;
        fuelLevel = 0;
    }

    public void start() {
        if (fuelLevel > 0 && validateFuel(requiredFuelType)) {
            running = true;
        } else {
            throw new IllegalStateException("Not able to start engine.");
        }
    }

    protected abstract boolean validateFuel(FuelType fuelType);

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void fill(FuelType fuelType, int fuelLevel) {
        if (!validateFuel(fuelType)) {
            throw new IllegalStateException("Not able to fill engine, inappropriate fuel type");
        }

        this.fuelType = fuelType;

        if (fuelLevel >= 0 && fuelLevel <= 100) {
            this.fuelLevel = fuelLevel;
        } else if (fuelLevel > 100) {
            this.fuelLevel = 100;
        } else {
            this.fuelLevel = 0;
        }
    }

    public FuelType getFuelType() {
        return  requiredFuelType;
    }

    public EngineType getEngineType() {
        return engineType;
    }

}
