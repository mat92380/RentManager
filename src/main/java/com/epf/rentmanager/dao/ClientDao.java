package com.epf.rentmanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ClientDao {

	private static ClientDao instance = null;

	private ClientDao() {}


	private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client(nom, prenom, email, naissance) VALUES(?, ?, ?, ?);";
	private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
	private static final String FIND_CLIENT_QUERY = "SELECT nom, prenom, email, naissance FROM Client WHERE id=?;";
	private static final String FIND_CLIENTBYMAIL_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client WHERE email=?;";
	private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, naissance FROM Client;";
	private static final String UPDATE_CLIENT_QUERY = "UPDATE Client SET nom=?, prenom=?, email=?, naissance=? WHERE id=?;";

	/**
	 * Permets de creer un client
	 * @param client
	 * @return
	 * @throws DaoException
	 */
	public long create(Client client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps =
					 connection.prepareStatement(CREATE_CLIENT_QUERY,Statement.RETURN_GENERATED_KEYS)){

			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ps.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			int id = 0;
			if (resultSet.next()) {
				id = resultSet.getInt(1);
			}
			return id;
		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	/**
	 * Permets de modifier un client
	 * @param client
	 * @return
	 * @throws DaoException
	 */
	public long edit(Client client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps =
					 connection.prepareStatement(UPDATE_CLIENT_QUERY)){
			ps.setString(1, client.getNom());
			ps.setString(2, client.getPrenom());
			ps.setString(3, client.getEmail());
			ps.setDate(4, Date.valueOf(client.getNaissance()));
			ps.setInt(5,client.getId());
			long pse = ps.executeUpdate();
			return pse;

		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	/**
	 * Permets de supprimer un client
	 * @param id_client
	 * @return
	 * @throws DaoException
	 */
	public long delete(int id_client) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps =
					 connection.prepareStatement(DELETE_CLIENT_QUERY)){

			ps.setInt(1, id_client);
			return ps.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException();
		}
	}

	/**
	 * Permets de trouver un client grace à son id
	 * @param id
	 * @return le client recherché
	 * @throws DaoException
	 */
	public Client findById(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement pstatement = connection.prepareStatement(FIND_CLIENT_QUERY)){
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();
				return new Client( (int) id, nom, prenom, email, naissance);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	/**
	 * Permets de trouver un client à partir de son mail
	 * @param email
	 * @return le client recherché
	 * @throws DaoException
	 */
	public Client findBymail(String email) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement pstatement = connection.prepareStatement(FIND_CLIENTBYMAIL_QUERY)) {
			pstatement.setString(1, email);
			ResultSet rs = pstatement.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();
				return new Client((int) id, nom, prenom, email, naissance);
			}else{
				pstatement.close();
				connection.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

	/**
	 * Permets de donner une liste de tous les clients
	 * @return l'ensemble des clients
	 * @throws DaoException
	 */
	public List<Client> findAll() throws DaoException {
		List<Client> clients = new ArrayList<Client>();
		try (Connection connection = ConnectionManager.getConnection();
			 Statement statement = connection.createStatement()){
			ResultSet rs = statement.executeQuery(FIND_CLIENTS_QUERY);
			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				LocalDate naissance = rs.getDate("naissance").toLocalDate();
				clients.add(new Client(id, nom,prenom,email,naissance));
			}

		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return clients;
	}

}


