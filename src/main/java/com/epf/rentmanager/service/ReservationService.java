package com.epf.rentmanager.service;

import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import java.util.List;

public class ReservationService {

    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }
    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }

    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une réservation
        try {
            return ReservationDao.getInstance().create(reservation);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }

    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {
        // TODO: récupérer un véhicule par son id
        try {
            return ReservationDao.getInstance().findResaByClientId(clientId);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {
        // TODO: récupérer un véhicule par son id
        try {
            return ReservationDao.getInstance().findResaByVehicleId(vehicleId);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    public List<Reservation> findAll() throws ServiceException {
        // TODO: récupérer tous les reservations
        try {
            return ReservationDao.getInstance().findAll();

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

}
