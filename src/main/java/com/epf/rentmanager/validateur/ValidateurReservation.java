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
                if (ireservation.getFin().until(reservation.getFin(), ChronoUnit.DAYS) > 0 && reservation.getDebut().until(ireservation.getFin(), ChronoUnit.DAYS) > 0 ||
                        reservation.getFin().until(ireservation.getFin(), ChronoUnit.DAYS) > 0 && ireservation.getDebut().until(reservation.getFin(), ChronoUnit.DAYS) > 0) return false;
            }
            return true;
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Verifie si la voiture est louer plus de 30 jours d'affilés
     * @param vehicleId
     * @param reservationService
     * @return true si la voiture peut etre louer, false sinon
     */
    public static boolean isrentless30days(int vehicleId,ReservationService reservationService) {
        try {
            List<Reservation> reservations = reservationService.findResaByVehicleId(vehicleId);
            reservations.sort((resa1, resa2) -> resa2.getDebut().compareTo(resa1.getDebut()));
            LocalDate now = LocalDate.now();
            LocalDate prevDate = now;
            for (Reservation resa : reservations) {
                if (resa.getDebut().until(now, ChronoUnit.DAYS) >= 30) return false;
                if (resa.getFin().until(prevDate, ChronoUnit.DAYS) > 1) return true;
                prevDate = resa.getDebut();
            }
            return true;
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }
    }
}
