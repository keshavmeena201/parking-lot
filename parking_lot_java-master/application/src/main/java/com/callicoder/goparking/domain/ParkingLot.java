package com.callicoder.goparking.domain;

import com.callicoder.goparking.exceptions.ParkingLotFullException;
import com.callicoder.goparking.exceptions.SlotNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingLot {
    private final int numSlots;
    private final int numFloors;
    private SortedSet<ParkingSlot> availableSlots = new TreeSet<>();
    private Set<ParkingSlot> occupiedSlots = new HashSet<>();

    public ParkingLot(int numSlots, int numflor) {
        if(numSlots <= 0) {
            throw new IllegalArgumentException("Number of slots in the Parking Lot must be greater than zero.");
        }

        // Assuming Single floor since only numSlots are specified in the input.
        this.numSlots = numSlots;
        this.numFloors = numflor;
        for (int i = 0; i< numflor; i++) {
            for (int j = 0; j < numSlots; j++) {
                ParkingSlot parkingSlot = new ParkingSlot(j + 1, i+1);
                this.availableSlots.add(parkingSlot);
            }
        }
        System.out.println(this.availableSlots.size());
    }

    public synchronized Ticket reserveSlot(Car car) {
        if(car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }

        if(this.isFull()) {
            //createNewParkingFloor();
            throw new ParkingLotFullException();
        }

        ParkingSlot nearestSlot = this.availableSlots.first();

        nearestSlot.reserve(car);
        this.availableSlots.remove(nearestSlot);
        this.occupiedSlots.add(nearestSlot);

        return new Ticket(nearestSlot.getSlotNumber(), nearestSlot.getFloorNumber(), car.getRegistrationNumber(), car.getColor());
    }

    public ParkingSlot leaveSlot(int slotNumber) {
        Optional<ParkingSlot> parkingSlot = this.occupiedSlots.stream().filter(slot -> slot.getSlotNumber() == slotNumber).findFirst();
        if (!parkingSlot.isPresent()) {
            throw new SlotNotFoundException(slotNumber);
        }

        this.availableSlots.add(parkingSlot.get());
        this.occupiedSlots.remove(parkingSlot.get());
        parkingSlot.get().clear();
        return parkingSlot.get();
    }

    public boolean isFull() {
        return this.availableSlots.isEmpty();
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        return this.occupiedSlots.stream()
                .filter(slot -> slot.getCar().getColor().equals(color))
                .map(slot -> slot.getCar().getRegistrationNumber())
                .collect(Collectors.toList());

        //TODO: implement getRegistrationNumbersByColor
        //return null;
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        //TODO: implement getSlotNumbersByColor
        return null;
    }

    public Optional<Integer> getSlotNumberByRegistrationNumber(String registrationNumber) {
        return this.occupiedSlots.stream()
                .filter(slot -> slot.getCar().getRegistrationNumber().equals(registrationNumber))
                .map(slot -> slot.getSlotNumber())
                .findFirst();
        //TODO: implement getSlotNumberByRegistrationNumber
        //return null;
    }

    private void createNewParkingFloor() {
        for(int i = 0; i < numSlots; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i+1, this.numFloors);
            this.availableSlots.add(parkingSlot);
        }
    }


    public int getNumSlots() {
        return numSlots;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public SortedSet<ParkingSlot> getAvailableSlots() {
        return availableSlots;
    }

    public Set<ParkingSlot> getOccupiedSlots() {
        return occupiedSlots;
    }
}
