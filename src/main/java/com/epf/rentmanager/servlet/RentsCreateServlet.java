package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validateur.ValidateurReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/rents/create")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class RentsCreateServlet extends HomeServlet{

    private static final long serialVersionUID = 1L;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ReservationService reservationService;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            request.setAttribute("vehicles", vehicleService.findAll());
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            request.setAttribute("clients", clientService.findAll());
            System.out.println();

        }catch (ServiceException e ){
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            final Reservation reservation = new Reservation();
            int idclient = Integer.parseInt(request.getParameter("client"));
            int idvoiture =  Integer.parseInt(request.getParameter("car"));
            LocalDate datedebut = LocalDate.parse(request.getParameter("begin"));
            LocalDate datefin = LocalDate.parse(request.getParameter("end"));
            reservation.setClient_id(idclient);
            reservation.setVehicle_id(idvoiture);
            reservation.setDebut(datedebut);
            reservation.setFin(datefin);
            if(ValidateurReservation.over7days(reservation) == false ){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "location max de 7 jours \n";
                response.getWriter().write(errorMessage);
            }
            if(ValidateurReservation.isfree(reservation, reservationService)== false){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "la voiture n'est pas disponible à ces dates\n";
                response.getWriter().write(errorMessage);
            }
            if(ValidateurReservation.isrent30days(reservation, reservationService)== false){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "la voiture doit avoir au moins un jour de diponible sur le mois\n";
                response.getWriter().write(errorMessage);
            }
            if(ValidateurReservation.isfree(reservation, reservationService)== false||ValidateurReservation.over7days(reservation) == false){
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "Merci de retourner à la page précedente et corriger les données saisies\n";
                response.getWriter().write(errorMessage);
            }


            else{
                reservationService.create(reservation);
                response.sendRedirect("/rentmanager/rents");
            }
        }catch (ServiceException e ){
            e.printStackTrace();
        }
    }
}
