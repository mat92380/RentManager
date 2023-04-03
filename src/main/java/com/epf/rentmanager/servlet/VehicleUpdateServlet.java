package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
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

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {
    @Autowired
    private VehicleService vehicleService;
    private static final long serialVersionUID = 1L;
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/vehicles/update.jsp");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            //System.out.println(id);
            request.setAttribute("vehicle", vehicleService.findById(id));

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
        dispatcher.forward(request, response);

        //response.sendRedirect("../cars/update");

        //response.sendRedirect("../users/update");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            final Vehicle vehicle = new Vehicle();
            String idc = request.getParameter("id");
            //System.out.println("idc "+idc);
            int id = Integer.parseInt(idc);
            //System.out.println("id "+id);
            String constructeur = request.getParameter("manufacturer");

            String nb = request.getParameter("seats");
            int nb_places = Integer.parseInt(nb);




            /*System.out.println(id);*/
            vehicle.setId(id);
            vehicle.setConstructeur(constructeur);

            vehicle.setNb_place(nb_places);
            //System.out.println(vehicle);
            vehicleService.edit(vehicle);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        //this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
        //permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
        response.sendRedirect("/rentmanager/cars");
    }
}
