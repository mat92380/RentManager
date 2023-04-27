package com.epf.rentmanager.validateur;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ValidateurReservation {
    /**
     * Verifie si la voiture est loué plus de 7 jours
     * @param reservation
     * @return true si la voiture est reservé moins de 7 jours, false sinon
     */
    public static boolean over7days(Reservation reservation){

        if (ChronoUnit.DAYS.between(reservation.getDebut(),reservation.getFin())>7){
            return false;
        }
        else {return true;}
    }

    /**
     * Verifie si la voiture est disponible
     * @param reservation
     * @param reservationService
     * @return true si la voiture est disponible,false sinon
     */
    public static boolean isfree(Reservation reservation, ReservationService reservationService){
        List<Reservation> reservations;
        try {
            reservations = reservationService.findResaByVehicleId(reservation.getVehicle_id());
            for (Reservation ireservation : reservations) {
                if ((reservation.getDebut().isAfter(ireservation.getDebut()) && reservation.getDebut().isBefore(ireservation.getFin())) ||
                        (reservation.getFin().isAfter(ireservation.getDebut()) && reservation.getFin().isBefore(ireservation.getFin())) ||
                        (reservation.getDebut().isBefore(ireservation.getDebut()) && reservation.getFin().isAfter(ireservation.getFin())))
                return false;
            }
            return true;
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }
    }


}
