package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private ReservationService reservationService;
	/**
	 * Permets de créer un véhicule
	 * @param vehicle
	 * @return
	 * @throws DaoException
	 */
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule
		try {
			return vehicleDao.create(vehicle);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de supprimer un vehicule et les réseravtion associés
	 * @param vehicle_id
	 * @return
	 * @throws DaoException
	 */
	public long delete(int vehicle_id) throws ServiceException {
		// TODO: Supprimer un véhicule
		try {
			List<Reservation> listresavehicle = reservationService.findResaByVehicleId(vehicle_id);
			for(Reservation reservation : listresavehicle) {
				reservationService.delete(reservation.getId());
			}
			return vehicleDao.delete(vehicle_id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de modifier une véhicule
	 * @param vehicle
	 * @return
	 * @throws DaoException
	 */
	public long edit(Vehicle vehicle) throws ServiceException {
		// TODO: modifier un véhicule
		try {
			return vehicleDao.edit(vehicle);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	/**
	 * Permets de trouver un véhicule à partir de son identifiant
	 * @param id
	 * @return le vehicule qui correspond à l'identifiant
	 * @throws DaoException
	 */
	public Vehicle findById(long id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		try {
			return vehicleDao.findById(id);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de récupérer tout les vehicule
	 * @return la liste de tous les véhicule
	 * @throws DaoException
	 */
	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return vehicleDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	/**
	 * Permets de compter le nombre de vehicule
	 * @return le nombre de vehicule
	 * @throws ServiceException
	 */
	public int count() throws ServiceException {
		// TODO: compter les clients
		List<Vehicle> vehicles = this.findAll();
		return vehicles.size();
	}
	/**
	 * Permets de trouver tout les vehicule déja réservé par un client
	 * @param client_id
	 * @return la liste des vehicules utilisés par le client
	 * @throws DaoException
	 */
	public List<Vehicle> findByClientId(int client_id) throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return vehicleDao.findByClientId(client_id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
}
