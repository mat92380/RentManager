package com.epf.rentmanager.validateur;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class ValidateurReservation {
    public static boolean over7days(Reservation reservation){

        if (ChronoUnit.DAYS.between(reservation.getDebut(),reservation.getFin())>7){
            return false;
        }
        else {return true;}
    }
    public static boolean isfree(Reservation reservation, ReservationService reservationService){
        List<Reservation> reservations;
        try {
            reservations = reservationService.findResaByVehicleId(reservation.getVehicle_id());
            for (Reservation ireservation : reservations) {
                if (ireservation.getFin().until(reservation.getFin(), ChronoUnit.DAYS) > 0 && reservation.getDebut().until(ireservation.getFin(), ChronoUnit.DAYS) > 0 ||
                        reservation.getFin().until(ireservation.getFin(), ChronoUnit.DAYS) > 0 && ireservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS) > 0) return false;
            }
            return true;
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }
    }
}
