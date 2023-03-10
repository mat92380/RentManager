package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;

public class VehicleService {

	private VehicleDao vehicleDao;
	public static VehicleService instance;
	
	private VehicleService() {
		this.vehicleDao = VehicleDao.getInstance();
	}
	
	public static VehicleService getInstance() {
		if (instance == null) {
			instance = new VehicleService();
		}
		
		return instance;
	}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		// TODO: créer un véhicule
		try {
			return VehicleDao.getInstance().create(vehicle);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public Vehicle findById(long id) throws ServiceException {
		// TODO: récupérer un véhicule par son id
		try {
			return VehicleDao.getInstance().findById(id);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}

	}

	public List<Vehicle> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return VehicleDao.getInstance().findAll();

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
