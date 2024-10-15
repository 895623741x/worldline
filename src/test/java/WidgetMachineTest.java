import com.worldline.interview.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;

class WidgetMachineTest {

    // Test producing widgets with a Petrol Engine
    @Test
    public void testProduceWidgetsWithPetrolEngine() {
        Engine engine = new InternalCombustionEngine(FuelType.PETROL);
        engine.fill(FuelType.PETROL, 100); // Fill with valid fuel
        WidgetMachine machine = new WidgetMachine(engine);

        BigDecimal cost = machine.produceWidgets(16);

        assertEquals(new BigDecimal("18.00"), cost); // 2 batches with diesel cost (9.00 * 2)
    }

    // Test producing widgets with a Diesel Engine
    @Test
    public void testProduceWidgetsWithDieselEngine() {
        Engine engine = new InternalCombustionEngine(FuelType.DIESEL);
        engine.fill(FuelType.DIESEL, 100); // Fill with valid fuel
        WidgetMachine machine = new WidgetMachine(engine);

        BigDecimal cost = machine.produceWidgets(16); // Testing for multiple batches

        assertEquals(new BigDecimal("24.00"), cost);  // 2 batches with diesel cost (12.00 * 2)
    }

    // Test producing widgets with a Steam Engine (Coal)
    @Test
    public void testProduceWidgetsWithSteamEngineCoal() {
        Engine engine = new SteamEngine(FuelType.COAL);
        engine.fill(FuelType.COAL, 100); // Fill with valid fuel
        WidgetMachine machine = new WidgetMachine(engine);

        BigDecimal cost = machine.produceWidgets(4); // Testing for multiple batches

        assertEquals(new BigDecimal("11.30"), cost);  // 2 batches with coal cost (5.65 * 2)
    }

    // Test producing widgets with a Steam Engine (Wood)
    @Test
    public void testProduceWidgetsWithSteamEngineWood() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 100); // Fill with valid fuel
        WidgetMachine machine = new WidgetMachine(engine);

        BigDecimal cost = machine.produceWidgets(4); // Testing for multiple batches

        assertEquals(new BigDecimal("8.70"), cost);  // 2 batches with wood cost (4.35 * 2)
    }

    // Test invalid fuel exception
    @Test
    public void testInvalidFuelForEngine() {
        Engine engine = new SteamEngine(FuelType.COAL);
        assertThrows(IllegalStateException.class, () -> {
            engine.fill(FuelType.DIESEL, 50);  // Attempt to fill steam engine with Diesel
        });
    }

    // Test engine start failure due to lack of fuel
    @Test
    public void testEngineStartFailureDueToNoFuel() {
        Engine engine = new SteamEngine(FuelType.COAL);
        assertThrows(IllegalStateException.class, () -> {
            engine.start();  // Attempt to start the engine with no fuel
        });
    }
}
