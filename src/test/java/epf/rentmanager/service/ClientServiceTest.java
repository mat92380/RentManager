package epf.rentmanager.service;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epf.rentmanager.service.ClientService;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;


public class ClientServiceTest {




        @Test

        void isCreate() {

            // Given​

            Client legalclient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.now());
            ClientService clientService = new ClientService();

            //assert(clientService.create(legalclient));



            // Then​



        }



        @Test

        void isLegal_should_return_false_when_age_is_under_18() {

            // Given   ​

            /*User illegaUser = new User("John", "Doe", "john.doe@ensta.fr", 17);



            // Then​

            assertFalse(Users.isLegal(illegaUser));*/

        }


}
