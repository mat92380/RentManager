package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class ReservationService {
    @Autowired
    private ReservationDao reservationDao;
    //public static ReservationService instance;


   /* private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }*/
   /* public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }*/

    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une réservation
        try {
            return reservationDao.create(reservation);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {
        // TODO: récupérer un véhicule par son id
        try {
            return reservationDao.findResaByClientId(clientId);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {
        // TODO: récupérer un véhicule par son id
        try {
            return reservationDao.findResaByVehicleId(vehicleId);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    public long delete(int reservation_id) throws ServiceException {
        // TODO: créer un véhicule
        try {
            return reservationDao.delete(reservation_id);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    public List<Reservation> findAll() throws ServiceException {
        // TODO: récupérer tous les reservations
        try {
            return reservationDao.findAll();

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    public int count() throws ServiceException {
        //int counter =0;
        List<Reservation> reservations = this.findAll();
        //System.out.println(reservations.size());
        return reservations.size();
    }
    public List<Integer> findvehiclebyIdclientwithresa(int id_client){

        try {
            List<Reservation> reservations  = reservationDao.findAll();
            List<Integer> listidvehicle = Collections.emptyList();
            for (Reservation reservation : reservations) {
                if (reservation.getClient_id()==id_client) {
                    listidvehicle.add(reservation.getVehicle_id());
                    System.out.println(listidvehicle);
                }
            }
            return listidvehicle;
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }


    }
}
