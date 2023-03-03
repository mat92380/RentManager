package com.epf.rentmanager.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//une servlet par url
@WebServlet("/cars")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class VehiclesListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
        //permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
    }
}
