package com.epf.rentmanager.validateur;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateurClient {
    /*@Autowired
    ClientService clientService;*/


    public static boolean islegal(Client client){
        return client.getAge()>=18;
    }


    public static boolean nameisvalid(Client client) {
        if (client.getNom().length()>=3 && client.getPrenom().length()>=3){
            return true;
        }else{
            return false;
        }
    }

    public static boolean mailisvalid(Client client, ClientService clientService){
        boolean mailisvalid = true;
        try{
            Client clientmailtest = clientService.findBymail(client.getEmail());
            if (clientmailtest != null && clientmailtest.getEmail()==client.getEmail()){
                System.out.println("identique");
                mailisvalid= false;
            }
        }catch (ServiceException e ){
            e.printStackTrace();
        }
        return mailisvalid;
    }
}
