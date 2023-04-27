package com.epf.rentmanager.dao;

import java.sql.*;
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
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao {
	
	private static VehicleDao instance = null;
	private VehicleDao() {}
	private static final String CREATE_VEHICLE_QUERY = "INSERT INTO Vehicle(constructeur, nb_places) VALUES(?, ?);";
	private static final String DELETE_VEHICLE_QUERY = "DELETE FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLE_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle WHERE id=?;";
	private static final String FIND_VEHICLES_QUERY = "SELECT id, constructeur, nb_places FROM Vehicle;";
	private static final String UPDATE_VEHICLE_QUERY = "UPDATE Vehicle SET constructeur=?, nb_places=? WHERE id=?;";
	private static final String FIND_VEHICLES_BY_CLIENT = "SELECT * FROM Vehicle INNER JOIN Reservation ON Reservation.vehicle_id=Vehicle.id WHERE Reservation.client_id=?;";

	/**
	 * Permets de créer un véhicule
	 * @param vehicle
	 * @return
	 * @throws DaoException
	 */
	public long create(Vehicle vehicle) throws DaoException {
		try(Connection connection = ConnectionManager.getConnection();
			PreparedStatement ps =
					connection.prepareStatement(CREATE_VEHICLE_QUERY,Statement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, vehicle.getConstructeur());
			ps.setInt(2, vehicle.getNb_place());
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
	 * Permets de modifier une véhicule
	 * @param vehicle
	 * @return
	 * @throws DaoException
	 */
	public long edit(Vehicle vehicle) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps =
					 connection.prepareStatement(UPDATE_VEHICLE_QUERY)){
			ps.setString(1, vehicle.getConstructeur());
			ps.setString(2, Integer.toString(vehicle.getNb_place()));
			ps.setInt(3,vehicle.getId());
			long pse = ps.executeUpdate();
			return pse;
		} catch (SQLException e) {
			throw new DaoException();
		}

	}

	/**
	 * Permets de supprimer un vehicule
	 * @param vehicle_id
	 * @return
	 * @throws DaoException
	 */
	public long delete(int vehicle_id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection()){
			PreparedStatement ps =
					connection.prepareStatement(DELETE_VEHICLE_QUERY);
			ps.setInt(1, vehicle_id);
			ps.execute();
			if (ps.executeUpdate() != 0) {
				ps.close();
				connection.close();
				return 1;
			} else {
				return 0;
			}
		}
		catch (SQLException e) {
			throw new DaoException();
		}
	}

	/**
	 * Permets de trouver un véhicule à partir de son identifiant
	 * @param id
	 * @return le vehicule qui correspond à l'identifiant
	 * @throws DaoException
	 */
	public Vehicle findById(long id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement pstatement = connection.prepareStatement(FIND_VEHICLE_QUERY)){
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			String constructeur = rs.getString("constructeur");
			int nb_Places = rs.getInt("nb_Places");
			return new Vehicle( (int) id, constructeur, nb_Places);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
	}

	/**
	 * Permets de récupérer tout les vehicule
	 * @return la liste de tous les véhicule
	 * @throws DaoException
	 */
	public List<Vehicle> findAll() throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try (Connection connection = ConnectionManager.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(FIND_VEHICLES_QUERY)){
			while (rs.next()) {
				int id = rs.getInt("id");
				String constructeur = rs.getString("constructeur");
				int nb_Places = rs.getInt("nb_Places");
				vehicles.add(new Vehicle(id, constructeur, nb_Places));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
			}
			return vehicles;
		}

	/**
	 * Permets de trouver tout les vehicule déja réservé par un client
	 * @param client_id
	 * @return la liste des vehicules utilisés par le client
	 * @throws DaoException
	 */
	public List<Vehicle> findByClientId(long client_id) throws DaoException {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try (
				Connection connection = ConnectionManager.getConnection();
				PreparedStatement pstatement = connection.prepareStatement(FIND_VEHICLES_BY_CLIENT);
		) {
			pstatement.setLong(1, client_id);
			ResultSet resultSet = pstatement.executeQuery();
			while(resultSet.next())
				pstatement.setLong(1, client_id);
			ResultSet rs = pstatement.executeQuery();
			while(rs.next())
				vehicles.add(new Vehicle(rs.getInt("id"), rs.getString("constructeur"),rs.getInt("nb_places")));
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();

		}
		return vehicles;
	}
	}
	


