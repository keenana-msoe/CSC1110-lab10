/*
 * Course: CSC1110 131
 * Fall 2023
 * Lab 10 - ParkingLots
 * Name: Andrew Keenan
 * Created: 10-30-23
 */
package keenana;

import java.util.ArrayList;

/**
 * Manages parking lots within a district.
 * @author Andrew keenan
 */
public class District {
    private int timestamp1;
    private int totalTimeClosed;
    private ArrayList<ParkingLot> lots;

    /**
     * Set up a district with parking lots.
     */
    public District(){
        lots = new ArrayList<>();
    }

    /**
     * adding a lot to the array
     * @param name name of the lot
     * @param capacity capacity of the lot
     * @return the index of the lot or if it was not added -1
     */
    public int addLot(String name, int capacity){
        lots.add(new ParkingLot(name, capacity));
        return lots.size() - 1;
    }

    /**
     * return the parking lot based off of the index
     * @param index index of the desired parking lot
     * @return the parking lot
     */
    public ParkingLot getLot(int index){
        return lots.get(index);
    }
    /**
     * Display status information for all three lots.
     * @return string builder for each parking lot status
     * @see ParkingLot#toString()  for the format for each.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("District status:\n");
        for(int i = 0; i < lots.size(); i++){
            sb.append("  ").append(getLot(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the number of remaining parking spots in the district
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {
        int ret = 0;
        for(int i = 0; i < lots.size(); i++){
            ret += getLot(i).getNumberOfSpotsRemaining();
        }
        return ret;
    }

    /**
     * Returns the amount of time all three lots have been
     * simultaneously closed.
     * @return number of minutes all three lots have been closed
     */
    public int getMinutesClosed() {
        return totalTimeClosed;
    }

    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     * @return whether all three lots in the district are closed
     */
    public boolean isClosed() {
        for(int i = 0; i < lots.size(); i++){
            if(!getLot(i).isClosed()){
                return false;
            }
        }
        return true;
    }

    /**
     * Record a vehicle entering a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleEntry for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleEntry on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {
        boolean wasClosed = isClosed();
        getLot(lotNumber).markVehicleEntry(timestamp);
        if (!wasClosed && isClosed()){
            timestamp1 = timestamp;
        }
    }

    /**
     * Record a vehicle exiting a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleExit for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleExit on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Exit timestamp in minutes since all lots were opened.
     */
    public void markVehicleExit(int lotNumber, int timestamp) {
        boolean wasClosed = isClosed();
        int timestamp2;
        getLot(lotNumber).markVehicleExit(timestamp);
        if (wasClosed && !isClosed()){
            timestamp2 = timestamp;
            totalTimeClosed += timestamp2 - timestamp1;
        }
    }
}
