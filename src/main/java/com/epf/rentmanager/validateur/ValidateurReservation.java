package com.epf.rentmanager.validateur;

import com.epf.rentmanager.model.Reservation;

import java.time.temporal.ChronoUnit;

public class ValidateurReservation {
    public static boolean over7days(Reservation reservation){

        if (ChronoUnit.DAYS.between(reservation.getDebut(),reservation.getFin())>7){
            return false;
        }
        else {return true;}
    }

}
