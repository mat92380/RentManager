package com.epf.rentmanager.servlet;

import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.dao.VehicleDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//une servlet par url
@WebServlet("/home")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class HomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private ClientDao clientDao = ClientDao.getInstance();
	private VehicleDao vehicleDao = VehicleDao.getInstance();
	private ReservationDao reservationDao = ReservationDao.getInstance();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try{
			request.setAttribute("nbClients", clientDao.count() );
			request.setAttribute("nbVehicles", vehicleDao.count());
			request.setAttribute("nbReservations", reservationDao.count());

		}catch (DaoException e ){
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
		//permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
	}

}
