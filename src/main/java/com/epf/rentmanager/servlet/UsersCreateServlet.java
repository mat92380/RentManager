package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/users/create")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class UsersCreateServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private ClientService clientService = ClientService.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        //permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
// traitement du formulaire (appel à la méthode de sauvegarde)
        try {
            final Client client = new Client();

            String nom = request.getParameter("last_name");
            String prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);
            client.setNaissance(naissance);
            clientService.create(client);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        //this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        response.sendRedirect("/rentmanager/users/create");
    }
    }
