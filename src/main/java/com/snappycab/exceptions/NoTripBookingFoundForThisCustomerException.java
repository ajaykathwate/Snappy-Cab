package com.snappycab.exceptions;

/**
 * @author Ajay Kathwate - 8/31/2023
 */
public class NoTripBookingFoundForThisCustomerException extends RuntimeException {

    public NoTripBookingFoundForThisCustomerException(){
        super("Cannot Book another ... As trip is already in Progress");
    }

}
