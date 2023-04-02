package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
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
	//public static VehicleService instance;
	
	/*private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}*/
	
	/*public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}*/
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule
		try {
			return vehicleDao.create(vehicle);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}
	public long delete(int vehicle_id) throws ServiceException {
		// TODO: créer un véhicule
		try {
			return vehicleDao.delete(vehicle_id);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public Vehicle findById(long id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		try {
			return vehicleDao.findById(id);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return vehicleDao.findAll();

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	public int count() throws ServiceException {
		//int counter =0;
		List<Vehicle> vehicles = this.findAll();
		//System.out.println(vehicles.size());
		return vehicles.size();
	}
	
}
