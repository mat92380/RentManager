package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/rents/create")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class RentsCreateServlet extends HomeServlet{
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService = VehicleService.getInstance();
    private ClientService clientService = ClientService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{

            request.setAttribute("vehicles", vehicleService.findAll());
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            //vehicles = vehicleService.findAll()
            request.setAttribute("clients", clientService.findAll());
            System.out.println();

        }catch (ServiceException e ){
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
        //permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
// traitement du formulaire (appel à la méthode de sauvegarde)
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
            reservationService.create(reservation);



        }catch (ServiceException e ){
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);

    }

}
