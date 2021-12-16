package com.callicoder.goparking.domain;

public class Ticket {
    private int slotNumber;
    private int floorNumber;
    private String registrationNumber;
    private String color;

    public Ticket(int slotNumber,int floorNumber, String registrationNumber, String color) {
        this.slotNumber = slotNumber;
        this.floorNumber = floorNumber;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public int getFloorNumber()  { return floorNumber; }
}
