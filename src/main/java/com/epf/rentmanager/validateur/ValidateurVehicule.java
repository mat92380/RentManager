package com.epf.rentmanager.validateur;

import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

public class ValidateurVehicule {

    /***
     * Vérifie si le nombre de place est compris entre 2 et 9
     * @param vehicle
     * @return true si le nombre de place est entre 2 et 9 false sinon
     *
     */
    public static boolean nmbrplaceisvalid(Vehicle vehicle){
        if (vehicle.getNb_place()>1 && vehicle.getNb_place()<10){
            return true;
        }else {
            return false;
        }
    }


}
