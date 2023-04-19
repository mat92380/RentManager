package com.epf.rentmanager.validateur;

import com.epf.rentmanager.model.Vehicle;

public class ValidateurVehicule {

    public static boolean nmbrplaceisvalid(Vehicle vehicle){
        if (vehicle.getNb_place()>1 && vehicle.getNb_place()<10){
            return true;
        }else {
            return false;
        }
    }
    public static boolean hasrest(Vehicle vehicle){
        return true;
    }
}
