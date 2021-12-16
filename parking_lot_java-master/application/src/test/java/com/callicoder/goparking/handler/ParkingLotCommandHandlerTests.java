package com.callicoder.goparking.handler;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.callicoder.goparking.utils.MessageConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotCommandHandlerTests {

    private static PrintStream sysOut;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setupStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void resetStream() {
        outContent.reset();
    }

    @Test
    public void testCreateParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(10);

        assertEquals(String.format("Created a parking lot with 10 slots\n"), outContent.toString());
        assertEquals(String.format(PARKING_LOT_CREATED_MSG, 10) + "\n", outContent.toString());
    }

    @Test
    public void testCreateMultipleParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(10);
        parkingLotCommandHandler.createParkingLot(6);

        assertTrue(outContent.toString().endsWith(PARKING_LOT_ALREADY_CREATED + "\n"));
    }


    @Test
    public void testParkOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(6);
        parkingLotCommandHandler.park("KA-01-HH-3141", "Black");

        assertTrue(outContent.toString().endsWith("Allocated slot number: 1\n"));
        assertEquals(String.format(PARKING_LOT_CREATED_MSG, 6) + "\n" +
                String.format(PARKING_SLOT_ALLOCATED_MSG, 1) + "\n", outContent.toString());
    }

    @Test
    public void testParkWithNoParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.park("KA-01-HQ-4669", "White");
        assertTrue(outContent.toString().endsWith(PARKING_LOT_NOT_CREATED + "\n"));
    }


    @Test
    public void testStatusWithNoParkingLotOutput() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.status();
        assertTrue(outContent.toString().endsWith(PARKING_LOT_NOT_CREATED + "\n"));
    }
    @Test
    public void testParkDuplicateVehicle() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(6);
        //assertTrue(outContent.toString().endsWith(PARKING_LOT_ALREADY_CREATED + "\n"));
        parkingLotCommandHandler.park("KA-01-HH-3141", "Black");

        assertTrue(outContent.toString().endsWith("Allocated slot number: 1\n"));
        parkingLotCommandHandler.park("KA-01-HH-3141", "White");
        assertTrue(outContent.toString().endsWith(DUPLICATE_VEHICLE_MESSAGE+"\n"));
    }

    @Test
    public void testParkingLotNotCreatedLeaveSlot() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.leave(0);
        assertTrue(outContent.toString().endsWith(PARKING_LOT_NOT_CREATED + "\n"));
    }

    @Test
    public void testParkingLotCreatednotOccupiedSlot() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(4);
        parkingLotCommandHandler.park("KA-01-HH-3141", "Black");
        parkingLotCommandHandler.leave(2);
        assertTrue(outContent.toString().endsWith("Slot Already free: 2\n"));
    }

    @Test
    public void testSlotOcuupiedLeave() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        parkingLotCommandHandler.createParkingLot(4);
        parkingLotCommandHandler.park("KA-01-HH-3141", "Black");
        parkingLotCommandHandler.park("KA-02-HH-3141", "White");
        //System.out.print(outContent);
        assertTrue(outContent.toString().endsWith("Allocated slot number: 2\n"));
        parkingLotCommandHandler.leave(2);
        assertTrue(outContent.toString().endsWith(String.format(PARKING_SLOT_LEAVE_MSG, 2)+"\n"));
    }
    @AfterAll
    public static void revertStreams() {
        System.setOut(sysOut);
    }
}
