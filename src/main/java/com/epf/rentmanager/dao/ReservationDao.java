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
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {

	private static ReservationDao instance = null;
	private ReservationDao() {}
	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";
	private static final String FIND_RESERVATIONS_BY_ID_QUERY = "SELECT id, client_id,vehicle_id, debut, fin FROM Reservation WHERE id=?;";
	private static final String UPDATE_RESERVATION_QUERY = "UPDATE Reservation SET client_id=?, vehicle_id=?, debut=?, fin=? WHERE id=?;";

	/**
	 * Permets de créer une réservation
	 * @param reservation
	 * @return
	 * @throws DaoException
	 */
	public long create(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps =
					 connection.prepareStatement(CREATE_RESERVATION_QUERY,Statement.RETURN_GENERATED_KEYS)){

			ps.setInt(1, reservation.getClient_id());
			ps.setInt(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
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
	 * Permets de modifier une réservation
	 * @param reservation
	 * @return
	 * @throws DaoException
	 */
	public long edit(Reservation reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement ps =
					 connection.prepareStatement(UPDATE_RESERVATION_QUERY);){

			ps.setInt(1, reservation.getClient_id());
			ps.setInt(2, reservation.getVehicle_id());
			ps.setDate(3, Date.valueOf(reservation.getDebut()));
			ps.setDate(4, Date.valueOf(reservation.getFin()));
			ps.setInt(5,reservation.getId());
			long pse = ps.executeUpdate();
			return pse;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	/**
	 * Permets de supprimer une reservation
	 * @param id_reservation
	 * @return
	 * @throws DaoException
	 */
	public long delete(int id_reservation) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
		PreparedStatement ps =
				connection.prepareStatement(DELETE_RESERVATION_QUERY)){
			ps.setInt(1, id_reservation);
			ps.execute();
			ps.execute();
			if (ps.executeUpdate() != 0) {
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
	 * Permets de trouver toute les réservation d'un client
	 * @param clientId
	 * @return la liste des réservation duy client dont on a l'id
	 * @throws DaoException
	 */
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_CLIENT_QUERY)){
			pstatement.setLong(1,clientId);
			ResultSet rs = pstatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id, (int) clientId, vehicle_id, debut, fin)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	/**
	 * Permets d'obtenir les reservations liées à un vehicule
	 * @param vehicleId
	 * @return liste de réservation associé à un vehicule
	 * @throws DaoException
	 */
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_VEHICLE_QUERY);){
			pstatement.setLong(1,vehicleId);
			ResultSet rs = pstatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id, client_id, (int)vehicleId, debut, fin)) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	/**
	 * Renvoie la liste de toutes les réservations
	 * @return liste de toute les réservations
	 * @throws DaoException
	 */
	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		try (Connection connection = ConnectionManager.getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet rs = statement.executeQuery(FIND_RESERVATIONS_QUERY);){

			while (rs.next()) {
				int id = rs.getInt("id");
				int client_id = rs.getInt("client_id");
				int vehicle_id = rs.getInt("vehicle_id");
				LocalDate debut = rs.getDate("debut").toLocalDate();
				LocalDate fin = rs.getDate("fin").toLocalDate();
				reservations.add(new Reservation(id, client_id, vehicle_id, debut, fin));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DaoException();
		}
		return reservations;
	}

	/**
	 * permets de retrouver une réservation à partir de son identifiant
	 * @param id
	 * @return la reservation qui corresponds à l'id
	 * @throws DaoException
	 */
	public Reservation findById(int id) throws DaoException {
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement pstatement = connection.prepareStatement(FIND_RESERVATIONS_BY_ID_QUERY)){
			pstatement.setLong(1,id);
			ResultSet rs = pstatement.executeQuery();
			rs.next();
			int vehicle_id = Integer.parseInt(rs.getString("vehicle_id"));
			int client_id = Integer.parseInt(rs.getString("client_id"));
			LocalDate debut = rs.getDate("debut").toLocalDate();
			LocalDate fin = rs.getDate("fin").toLocalDate();
			return new Reservation( (int) id, client_id, vehicle_id, debut, fin);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException();

		}
	}

}

