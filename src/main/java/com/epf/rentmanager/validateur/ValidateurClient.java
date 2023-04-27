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
    /**
     * vérifie si le client est majeur
     * @param client
     * @return true si le client à l'age légal, false sinon
     */
    public static boolean islegal(Client client){
        return client.getAge()>=18;
    }

    /**
     * verifie si le nom est valide
     * @param client
     * @return true si le nom est valide, false sinon
     */
    public static boolean nameisvalid(Client client) {
        if (client.getNom().length()>=3 && client.getPrenom().length()>=3){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Verifie si le mail est valide
     * @param client
     * @param clientService
     * @return true si le mail est valide, false sinon
     */
    public static boolean mailisvalid(Client client, ClientService clientService){
        boolean mailisvalid = true;
        try{
            Client clientmailtest = clientService.findBymail(client.getEmail());
            if (clientmailtest != null && clientmailtest.getEmail()==client.getEmail()){
                mailisvalid= false;
            }
        }catch (ServiceException e ){
            e.printStackTrace();
        }
        return mailisvalid;
    }
}
