package com.epf.rentmanager.servlet;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/users/details")
public class UsersDetailsServlet extends HttpServlet {

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

        final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
        try {
             int id = Integer.parseInt(request.getParameter("id").toString());
            final List<Reservation> reservations = reservationService.findResaByClientId(id);
            final List<Vehicle> vehicles = vehicleService.findByClientId(id);
            request.setAttribute("user", clientService.findById(id));
            request.setAttribute("reservations", reservations);
            request.setAttribute("vehicles", vehicles);
            request.setAttribute("countr", reservations.size());
            request.setAttribute("countv", vehicles.size());
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.sendRedirect("/rentmanager/users/details");
    }
}
