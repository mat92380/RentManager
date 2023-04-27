package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validateur.ValidateurClient;
import com.epf.rentmanager.validateur.ValidateurVehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/cars/create")
public class VehiclesCreateServlet extends VehiclesListServlet{
    private static final long serialVersionUID = 1L;
    @Autowired
    private VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
            try {
                final Vehicle vehicle = new Vehicle();
                String constructeur = request.getParameter("manufacturer");
                int nb_Places = Integer.parseInt(request.getParameter("seats"));
                vehicle.setConstructeur(constructeur);
                vehicle.setNb_place(nb_Places);
                if (ValidateurVehicule.nmbrplaceisvalid(vehicle)){
                    vehicleService.create(vehicle);
                    response.sendRedirect("/rentmanager/cars");
                }
                else{
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    String errorMessage = "Mauvais nombre de place";
                    response.getWriter().write(errorMessage);
                }
        }catch (ServiceException e ){
            e.printStackTrace();
        }
        }

    }

