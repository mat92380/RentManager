package epf.rentmanager.service;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.validateur.ValidateurClient;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


public class ClientServiceTest {




        @Test
        void islegalistrue() {



            Client illegalclient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.now());
            assertFalse(ValidateurClient.islegal(illegalclient));

        }
        @Test
        void mailisok(){
        Client illegalclient = new Client("John", "D", "john.doe@ensta.fr", LocalDate.now());
        assertFalse(ValidateurClient.nameisvalid(illegalclient));
    }






}
