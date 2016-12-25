/* Copyright (C) 2016 ASYX International B.V. All rights reserved. */
package com.parkinglot.model;

/**
 * @author trim
 * @version 1.0, Dec 23, 2016
 * @since
 */
public class Slot {

    private int slotNumber;
    private boolean park;
    private String regNumber;

    public Slot(int slotNumber) {
        park = false;
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isPark() {
        return park;
    }

    public void setPark(boolean park) {
        this.park = park;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

}

