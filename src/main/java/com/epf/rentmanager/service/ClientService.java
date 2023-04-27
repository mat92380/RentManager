package com.epf.rentmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private ReservationService reservationService;

	private ClientService(ClientDao clientDao){
		this.clientDao = clientDao;}

	public ClientService(){
		}

	/**
	 * Permets de creer un client
	 * @param client
	 * @return
	 * @throws DaoException
	 */
	public long create(Client client) throws ServiceException {
		// TODO: créer un client
		try {
			return clientDao.create(client);

		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de modifier un client
	 * @param client
	 * @return
	 * @throws DaoException
	 */
	public long edit(Client client) throws ServiceException {
		// TODO: mettre à jour un client
		try {
			return clientDao.edit(client);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	public Client findById(long id) throws ServiceException {
		// TODO: récupérer un client par son id
		try {
			return clientDao.findById(id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de trouver un client grace à son email
	 * @param email
	 * @return le client recherché
	 * @throws DaoException
	 */
	public Client findBymail(String email) throws ServiceException {
		// TODO: récupérer un client par son mail
		try {
			return clientDao.findBymail(email);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de supprimer un client et les réservations associés
	 * @param id_client
	 * @return
	 * @throws DaoException
	 */
	public long delete(int id_client) throws ServiceException {
			// TODO: supprimer un client
		try {
			List<Reservation> listresaclient = reservationService.findResaByClientId(id_client);
			for(Reservation reservation : listresaclient) {
				reservationService.delete(reservation.getId());
			}
			return clientDao.delete(id_client);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}
	/**
	 * Permets de donner une liste de tous les clients
	 * @return l'ensemble des clients
	 * @throws DaoException
	 */
	public List<Client> findAll() throws ServiceException {
		// TODO: récupérer tous les clients
		try {
			return clientDao.findAll();
		} catch (DaoException e) {
			e.printStackTrace();
			throw new ServiceException();
		}
	}

	/**
	 * Permets de compter le nombre client
	 * @return le nombre de client
	 * @throws ServiceException
	 */
	public int count() throws ServiceException {
		// TODO: Compte le nombre clients
		List<Client> clients = this.findAll();
		return clients.size();
	}
}
	

