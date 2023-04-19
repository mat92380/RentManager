package com.epf.rentmanager.servlet;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.io.IOException;
import java.time.LocalDate;


@WebServlet("/users/update")
public class UserUpdateServlet extends HttpServlet {
    @Autowired
    private ClientService clientService;

    private static final long serialVersionUID = 1L;
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);}



    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/update.jsp");
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            //System.out.println(id);
            request.setAttribute("user", clientService.findById(id));
            //System.out.println("user"+ clientService.findById(id));
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }
            dispatcher.forward(request, response);



        //response.sendRedirect("../users/update");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            final Client client = new Client();

            String idc = request.getParameter("id");

            int id = Integer.parseInt(idc);

            String nom = request.getParameter("last_name");
           /* System.out.println(nom);*/
            String prenom = request.getParameter("first_name");
            String email = request.getParameter("email");
            LocalDate naissance = LocalDate.parse(request.getParameter("naissance"));



            /*System.out.println(id);*/
            client.setId(id);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(email);
            client.setNaissance(naissance);
            
            clientService.edit(client);

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        //this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response);
        //permets de dire qu est ce qu on va envoyer vers la home servlet (homepage)
        response.sendRedirect("/rentmanager/users");
    }
}
