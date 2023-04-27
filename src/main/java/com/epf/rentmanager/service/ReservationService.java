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
    /**
     * Permets de créer une réservation
     * @param reservation
     * @return
     * @throws DaoException
     */
    public long create(Reservation reservation) throws ServiceException {
        // TODO: créer une réservation
        try {
            return reservationDao.create(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Permets de trouver toute les réservation d'un client
     * @param clientId
     * @return la liste des réservation duy client dont on a l'id
     * @throws DaoException
     */
    public List<Reservation> findResaByClientId(long clientId) throws ServiceException {
        // TODO: récupérer un réservation par l'id du client
        try {
            return reservationDao.findResaByClientId(clientId);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * permets de retrouver une réservation à partir de son identifiant
     * @param Id
     * @return la reservation qui corresponds à l'id
     * @throws DaoException
     */
    public Reservation findById(int Id) throws ServiceException {
        // TODO: récupérer une reservation par son id
        try {
            return reservationDao.findById(Id);

        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    /**
     * Permets d'obtenir les reservations liées à un vehicule
     * @param vehicleId
     * @return liste de réservation associé à un vehicule
     * @throws DaoException
     */
    public List<Reservation> findResaByVehicleId(long vehicleId) throws ServiceException {
        //TODO: récupérer un réservation par l'id du vehicule
        try {
            return reservationDao.findResaByVehicleId(vehicleId);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }

    }
    /**
     * Permets de supprimer une reservation
     * @param reservation_id
     * @return
     * @throws DaoException
     */
    public long delete(int reservation_id) throws ServiceException {
        // TODO: Supprimer un véhicule
        try {
            return reservationDao.delete(reservation_id);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Permets de modifier une réservation
     * @param reservation
     * @return
     * @throws DaoException
     */
    public long edit(Reservation reservation) throws ServiceException {
        // TODO: modifier un véhicule
        try {
            return reservationDao.edit(reservation);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }
    /**
     * Renvoie la liste de toutes les réservations
     * @return liste de toute les réservations
     * @throws DaoException
     */
    public List<Reservation> findAll() throws ServiceException {
        // TODO: récupérer toutes les reservations
        try {
            return reservationDao.findAll();
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException();
        }
    }

    /**
     * Compte le nombre de réservation
     * @return nombre de réservation
     * @throws ServiceException
     */
    public int count() throws ServiceException {
        // TODO: Compte le nombre de reservations
        List<Reservation> reservations = this.findAll();
        return reservations.size();
    }

}
