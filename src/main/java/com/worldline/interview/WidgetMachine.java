package com.worldline.interview;

import java.math.BigDecimal;

public class WidgetMachine {
    private Engine engine;

    public WidgetMachine(Engine engine) {
        this.engine = engine;
    }

    public BigDecimal produceWidgets(int quantity) {
        engine.start();
        BigDecimal cost = BigDecimal.ZERO;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private BigDecimal produce(int quantity) {

        int batch = 0;
        int batchCount = 0;
        BigDecimal costPerBatch = BigDecimal.ZERO;

        if (engine.getFuelType() == FuelType.PETROL) {
            costPerBatch = new BigDecimal("9.00");
        } else if (engine.getFuelType() == FuelType.DIESEL) {
            costPerBatch = new BigDecimal("12.00");
        } else if (engine.getFuelType() == FuelType.COAL) {
            costPerBatch = new BigDecimal("5.65");
        } else if (engine.getFuelType() == FuelType.WOOD) {
            costPerBatch = new BigDecimal("4.35");
        }

        while (batch < quantity) {

            if (engine.getEngineType() == EngineType.STEAM_ENGINE) {
                batch += 2;
            } else {
                batch += 8;
            }
            batchCount++;
        }

        return costPerBatch.multiply(new BigDecimal(batchCount));
    }

    public Engine getEngine() {
        return engine;
    }
}
