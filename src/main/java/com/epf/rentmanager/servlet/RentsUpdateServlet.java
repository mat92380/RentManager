package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/rents/update")
public class RentsUpdateServlet extends HttpServlet {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ClientService clientService;
    private static final long serialVersionUID = 1L;
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/update.jsp");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("vehicles", vehicleService.findAll());
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            request.setAttribute("clients", clientService.findAll());
            request.setAttribute("rents", reservationService.findById(id));
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            final Reservation reservation = new Reservation();
            String idc = request.getParameter("id");
            int id = Integer.parseInt(idc);
            int idclient = Integer.parseInt(request.getParameter("client"));
            int idvoiture =  Integer.parseInt(request.getParameter("car"));
            LocalDate datedebut = LocalDate.parse(request.getParameter("begin"));
            LocalDate datefin = LocalDate.parse(request.getParameter("end"));
            reservation.setClient_id(idclient);
            reservation.setVehicle_id(idvoiture);
            reservation.setDebut(datedebut);
            reservation.setFin(datefin);
            reservation.setId(id);
            reservationService.edit(reservation);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.sendRedirect("../rents");
    }
}
