package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class LeaveCommand implements Command {
    private ParkingLotCommandHandler parkingLotCommandHandler;

    public LeaveCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }
    @Override
    public String helpText() {
        return  "leave <slotNumber>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if(params.length != 1) {
            throw new InvalidParameterException("Expected two parameters <registrationNumber> and <color>");
        }
        parkingLotCommandHandler.leave(Integer.parseInt(params[0]));
    }
}
