package com.epf.rentmanager.servlet;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;
import com.epf.rentmanager.validateur.ValidateurClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

@WebServlet("/users/create")//Quand on va sur /home ca envoie vers la homepage grace à la servlet
public class UsersCreateServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    //private ClientService clientService = ClientService.getInstance();
    @Autowired
    private ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}

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

            /*String labelContent = request.getParameter("mssgerreurage");
            labelContent = labelContent.toUpperCase();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(labelContent);*/
            boolean erreurage = false;

            String nom = request.getParameter("last_name");
            String prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));


            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);
            client.setNaissance(naissance);


            if (ValidateurClient.islegal(client)==true && ValidateurClient.nameisvalid(client)==true){

                clientService.create(client);
                response.sendRedirect("/rentmanager/users");
                response.setStatus(HttpServletResponse.SC_OK);
            }
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
           /* if  (ValidateurClient.mailisvalid(client)==false ) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "mail deja utilisé";
                response.getWriter().write(errorMessage);
                //NE MARCHE PAS
            }*/

            /*else {
                *//*response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                String errorMessage = "Les conditions ne sont pas remplies";
                response.getWriter().write(errorMessage);*//*
                response.sendRedirect("/rentmanager/users/create"+ "?erreur=1");
            }*/


        } catch (ServiceException e) {
            e.printStackTrace();
        }
        //this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
        /*response.sendRedirect("/rentmanager/users");*/
    }
    }
