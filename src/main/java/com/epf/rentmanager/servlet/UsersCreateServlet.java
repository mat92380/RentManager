package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;
import com.epf.rentmanager.validateur.ValidateurClient;
import com.epf.rentmanager.validateur.ValidateurReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

@WebServlet("/users/create")
public class UsersCreateServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    @Autowired
    private ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
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
            if  (ValidateurClient.islegal(client)==false ) {
                //JOptionPane.showMessageDialog(null,"Erreur age","INfobox : "+ "erreur", JOptionPane.INFORMATION_MESSAGE);
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "Age trop bas\n";
                response.getWriter().write(errorMessage);
            }
            if  (ValidateurClient.nameisvalid(client)==false ) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "nom trop cours\n";
                response.getWriter().write(errorMessage);
            }
            if  (ValidateurClient.mailisvalid(client, clientService)==false ) {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "mail deja utilisé";
                response.getWriter().write(errorMessage);
            }
            else{
                clientService.create(client);
                response.sendRedirect("/rentmanager/users");
                response.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    }
