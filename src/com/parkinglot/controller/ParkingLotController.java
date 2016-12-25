/* Copyright (C) 2016 ASYX International B.V. All rights reserved. */
package com.parkinglot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.parkinglot.model.Car;
import com.parkinglot.model.Slot;

/**
 * @author trim
 * @version 1.0, Dec 23, 2016
 * @since
 */
public class ParkingLotController {

	private List<Slot> slots = new ArrayList<Slot>();
	private Map<Slot, Car> parkingSlots = new HashMap<>();

	/**
     * 
     */
	public ParkingLotController() {
		super();
	}

	public void parkingProcess() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input: ");
		while (scanner.hasNext()) {
			String[] input = scanner.nextLine().split("\\s+");
			Car car = new Car();
			if (input.length == 2 && "create_parking_lot".equals(input[0])) {
				createParkingSlots(Integer.parseInt(input[1]));
			}
			if (input.length == 2 && "leave".equals(input[0])
					&& !noParkingLot()) {
				leaveParkingSlots(Integer.parseInt(input[1]));
			}
			if (input.length == 2
					&& "registration_numbers_for_cars_with_colour"
							.equals(input[0]) && !noParkingLot()) {
				findRegNumberByColour(input[1]);
			}
			if (input.length == 2
					&& "slot_numbers_for_cars_with_colour".equals(input[0])
					&& !noParkingLot()) {
				findSlotNumberByColour(input[1]);
			}
			if (input.length == 2
					&& "slot_number_for_registration_number".equals(input[0])
					&& !noParkingLot()) {
				findSlotNumberByRegNumber(input[1]);
			}
			if (input.length == 3 && "park".equals(input[0]) && !noParkingLot()) {
				car.setPlateNumber(input[1]);
				car.setColour(input[2]);
				park(car);
			}
			if (input.length == 1 && "status".equals(input[0])
					&& !noParkingLot()) {
				statusParkingSlot(slots);
			}
			if (!"create_parking_lot".equals(input[0])
					&& !"park".equals(input[0])
					&& !"status".equals(input[0])
					&& !"leave".equals(input[0])
					&& !"registration_numbers_for_cars_with_colour"
							.equals(input[0])
					&& !"slot_numbers_for_cars_with_colour".equals(input[0])
					&& !"slot_number_for_registration_number".equals(input[0])) {
				System.out.println("Output: ");
				System.out
						.println(input[0]
								+ " is not recognized as a commands or your commands pattern was wrong");
				System.out.println("Input: ");
			}
			if (input.length > 3 || input.length <=1) {
				System.out.println("Output: ");
				System.out
						.println(input[0]
								+ " is not recognized as a commands or your commands pattern was wrong");
				System.out.println("Input: ");
			}
		}

	}

	private void findSlotNumberByRegNumber(String regNumber) {
		Iterator<Slot> slotIterator = slots.iterator();
		StringBuilder sb = new StringBuilder();
		while (slotIterator.hasNext()) {
			Slot slot = slotIterator.next();
			if (slot.isPark() && slot.getRegNumber().equals(regNumber)) {
				sb.append(slot.getSlotNumber());
			}
		}
		if (sb.length() != 0) {
			System.out.println("Output: ");
			System.out.println(sb);
			System.out.println("Input: ");
		} else {
			System.out.println("Output: ");
			System.out.println("Not found");
			System.out.println("Input: ");
		}

	}

	private void findSlotNumberByColour(String colour) {
		Iterator<Slot> slotIterator = slots.iterator();
		StringBuilder sb = new StringBuilder();
		while (slotIterator.hasNext()) {
			Slot slot = slotIterator.next();
			if (slot.isPark()) {
				Car car = parkingSlots.get(slot);
				if (colour.equals(car.getColour())) {
					sb.append(slot.getSlotNumber() + ", ");
				}
			}
		}
		if (sb.length() != 0) {
			System.out.println("Output: ");
			System.out.println(sb);
			System.out.println("Input: ");
		} else {
			System.out.println("Output: ");
			System.out.println("Not found");
			System.out.println("Input: ");
		}

	}

	private void findRegNumberByColour(String colour) {
		Iterator<Slot> slotIterator = slots.iterator();
		StringBuilder sb = new StringBuilder();
		while (slotIterator.hasNext()) {
			Slot slot = slotIterator.next();
			if (slot.isPark()) {
				Car car = parkingSlots.get(slot);
				if (colour.equals(car.getColour())) {
					sb.append(slot.getRegNumber() + ", ");
				}
			}
		}
		if (sb.length() != 0) {
			System.out.println("Output: ");
			System.out.println(sb);
			System.out.println("Input: ");
		} else {
			System.out.println("Output: ");
			System.out.println("Not found");
			System.out.println("Input: ");
		}
	}

	private boolean noParkingLot() {
		if (slots.isEmpty()) {
			System.out.println("Output: ");
			System.out
					.println("There is no parking lot, please create parking lot");
			System.out.println("Input: ");
			return true;
		}
		return false;
	}

	private void createParkingSlots(int size) {
		for (int i = 1; i <= size; i++) {
			slots.add(new Slot(i));
		}
		System.out.println("Output: ");
		System.out.println("Created a parking lot with " + slots.size()
				+ " slots");
		System.out.println("Input: ");
	}

	private void leaveParkingSlots(int slotNumber) {
		Iterator<Slot> slotIterator = slots.iterator();
		while (slotIterator.hasNext()) {
			Slot slot = slotIterator.next();
			if (slot.getSlotNumber() == slotNumber) {
				slot.setPark(false);
				slot.setRegNumber(null);
				System.out.println("Output: ");
				System.out.println("Slot number " + slotNumber + " is free");
				System.out.println("Input: ");
			}
		}
	}

	private Slot getFirstEmptySlot(List<Slot> listSlots) {
		Iterator<Slot> iteratorSlots = listSlots.iterator();
		boolean isAvailable = false;
		Slot emptySlot = null;
		while (iteratorSlots.hasNext() && !isAvailable) {
			emptySlot = iteratorSlots.next();
			if (!emptySlot.isPark()) {
				isAvailable = true;
			}
			if (emptySlot.isPark()
					&& emptySlot.getSlotNumber() == listSlots.size()) {
				return null;
			}
		}
		return emptySlot;
	}

	private void park(Car car) {
		Slot slot;
		if ((slot = getFirstEmptySlot(slots)) != null) {
			parking(slot, car);
		} else {
			System.out.println("Output : ");
			System.out.println("Sorry, parking lot is full ");
			System.out.println("Input : ");
		}
	}

	private void parking(Slot slot, Car car) {
		slot.setPark(true);
		slot.setRegNumber(car.getPlateNumber());
		parkingSlots.put(slot, car);
		System.out.println("Output : ");
		System.out.println("Allocated slot number: " + slot.getSlotNumber());
		System.out.println("Input : ");
	}

	private void statusParkingSlot(List<Slot> slots) {
		System.out.println("Output : ");
		System.out.println("Slot No \t Registration Number \t Colour");
		for (Slot slot : slots) {
			if (slot.isPark()) {
				Car car = parkingSlots.get(slot);
				System.out.println(slot.getSlotNumber() + " \t\t "
						+ slot.getRegNumber() + " \t\t " + car.getColour());
			}
		}
		System.out.println("Input : ");
	}

}
