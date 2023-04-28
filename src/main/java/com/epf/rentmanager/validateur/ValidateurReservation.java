package com.epf.rentmanager.validateur;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ReservationService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
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

    /**
     * Fonction qui permest de vérifier si la voiture que l'on veut louer dispose bien d'un jour de disponible dans les 30 jours
     * @param newReservation
     * @param reservationService
     * @return true si peut louer la voiture false sinon
     */
    public static boolean isrent30days(Reservation newReservation, ReservationService reservationService){
        try {
            List<Reservation> reservations = reservationService.findResaByVehicleId(newReservation.getVehicle_id());
            reservations.add(newReservation);
            Collections.sort(reservations, Comparator.comparing(Reservation::getDebut));
            LocalDate firstDate = reservations.get(0).getDebut();
            boolean isAvailableFor30Days = true;
            int cpt = 0;
            for (int i = 0; i < 31; i++) {
                LocalDate dateToCheck = firstDate.plusDays(i);
                boolean isReserved = false;
                for (Reservation listeReservation : reservations) {
                    if ((listeReservation.getDebut().isBefore(dateToCheck) || listeReservation.getDebut().isEqual(dateToCheck))
                            && (listeReservation.getFin().isAfter(dateToCheck) || listeReservation.getFin().isEqual(dateToCheck))) {
                        isReserved = true;
                        break;
                    }
                }
                if (!isReserved) {
                    cpt = 0;
                } else {
                    cpt++;
                    if (cpt > 30) {
                        isAvailableFor30Days = false;
                        break;
                    }
                }
            }
            return isAvailableFor30Days;}
        catch(ServiceException e) {
            e.printStackTrace();
            return false;

        }
    }

}
