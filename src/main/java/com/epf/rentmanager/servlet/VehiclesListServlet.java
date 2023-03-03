package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//une servlet par url

@WebServlet("/cars")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class VehiclesListServlet extends HomeServlet {
    private static final long serialVersionUID = 1L;
    private VehicleService vehicleService = VehicleService.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{

            request.setAttribute("vehicles", vehicleService.findAll());


        }catch (ServiceException e ){
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);


        //permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
    }
}
