import com.worldline.interview.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

class WidgetMachineTest {

    private Engine mockSteamEngine;
    private Engine mockInternalCombustionEngine;
    private WidgetMachine widgetMachine;

    @BeforeEach
    public void setUp() {
        mockSteamEngine = new SteamEngine(FuelType.WOOD);


        // Create the WidgetMachine with the mock Engine
        widgetMachine = new WidgetMachine(mockEngine);
    }

    @Test
    void testProduceWidgetsWithPetrol() {
        // Set up the mock engine for a petrol engine
        when(mockEngine.isRunning()).thenReturn(true);
        when(mockEngine.getFuelType()).thenReturn(FuelType.PETROL);
        when(mockEngine.getEngineType()).thenReturn(EngineType.INTERNAL_COMBUSTION_ENGINE);

        // Call the method to produce widgets
        BigDecimal cost = widgetMachine.produceWidgets(10);

        // Verify that the engine's start and stop methods were called
        verify(mockEngine).start();
        verify(mockEngine).stop();

        // Verify the cost for producing widgets with petrol
        assertEquals(new BigDecimal("18.00"), cost); // 2 batches for 10 widgets at $9.00 per batch
    }

    @Test
    void testProduceWidgetsWithDiesel() {
        // Set up the mock engine for a diesel engine
        when(mockEngine.isRunning()).thenReturn(true);
        when(mockEngine.getFuelType()).thenReturn(FuelType.DIESEL);
        when(mockEngine.getEngineType()).thenReturn(EngineType.INTERNAL_COMBUSTION_ENGINE);

        // Call the method to produce widgets
        BigDecimal cost = widgetMachine.produceWidgets(16);

        // Verify that the engine's start and stop methods were called
        verify(mockEngine).start();
        verify(mockEngine).stop();

        // Verify the cost for producing widgets with diesel
        assertEquals(new BigDecimal("24.00"), cost); // 2 batches for 16 widgets at $12.00 per batch
    }

    @Test
    void testProduceWidgetsWithSteamEngine() {
        // Set up the mock engine for a steam engine (e.g., using coal)
        when(mockEngine.isRunning()).thenReturn(true);
        when(mockEngine.getFuelType()).thenReturn(FuelType.COAL);
        when(mockEngine.getEngineType()).thenReturn(EngineType.STEAM_ENGINE);

        // Call the method to produce widgets
        BigDecimal cost = widgetMachine.produceWidgets(10);

        // Verify that the engine's start and stop methods were called
        verify(mockEngine).start();
        verify(mockEngine).stop();

        // Verify the cost for producing widgets with coal
        assertEquals(new BigDecimal("26.10"), cost); // 5 batches for 10 widgets at $4.35 per batch
    }

    @Test
    void testEngineDoesNotStart() {
        // Set up the mock engine to not start (not running)
        when(mockEngine.isRunning()).thenReturn(false);

        // Call the method to produce widgets
        BigDecimal cost = widgetMachine.produceWidgets(10);

        // Verify that the engine's start and stop methods were called
        verify(mockEngine).start();
        verify(mockEngine).stop();

        // Verify that no cost was incurred since the engine didn't start
        assertEquals(BigDecimal.ZERO, cost);
    }

    @Test
    void testInvalidFuelType() {
        // Set up the mock engine to use an invalid fuel type
        when(mockEngine.getFuelType()).thenReturn(FuelType.WOOD);
        when(mockEngine.isRunning()).thenReturn(true);
        when(mockEngine.getEngineType()).thenReturn(EngineType.STEAM_ENGINE);

        // Call the method to produce widgets
        BigDecimal cost = widgetMachine.produceWidgets(12);

        // Verify that the engine's start and stop methods were called
        verify(mockEngine).start();
        verify(mockEngine).stop();

        // Verify the cost for producing widgets with wood
        assertEquals(new BigDecimal("22.60"), cost); // 4 batches for 12 widgets at $5.65 per batch
    }

    @Test
    void testEngineStartFailureDueToFuel() {
        // Simulate an engine start failure (e.g., because of inappropriate fuel)
        doThrow(new IllegalStateException("Not able to start engine.")).when(mockEngine).start();

        // Call the method and expect an exception
        assertThrows(IllegalStateException.class, () -> {
            widgetMachine.produceWidgets(10);
        });

        // Verify that stop was not called since the engine didn't start
        verify(mockEngine, never()).stop();
    }
}
