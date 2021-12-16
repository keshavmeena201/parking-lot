package com.callicoder.goparking.interaction.command;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.LeaveCommand;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaveCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static LeaveCommand leaveCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        leaveCommand = new LeaveCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(InvalidParameterException.class, () -> leaveCommand.execute(params));
    }

    @Test
    public void executeWithMoreParameters() {
        String[] params = {"0", "1"};
        assertThrows(InvalidParameterException.class, () -> leaveCommand.execute(params));
    }

    @Test
    public void executeWithSingleParam() {
        String[] params = {"0"};
        assertDoesNotThrow(() -> leaveCommand.execute(params));
    }
}
