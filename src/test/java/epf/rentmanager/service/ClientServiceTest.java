package epf.rentmanager.service;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.DaoException;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.AppConfiguration;
import com.epf.rentmanager.model.Client;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.validateur.ValidateurClient;
import org.h2.engine.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ClientServiceTest {


        @Test
        void islegalistrue() {



            Client illegalclient = new Client("John", "Doe", "john.doe@ensta.fr", LocalDate.now());
            assertFalse(ValidateurClient.islegal(illegalclient));

        }
        @Test
        void nameisok(){
        Client illegalclient = new Client("John", "D", "john.doe@ensta.fr", LocalDate.now());
        assertFalse(ValidateurClient.nameisvalid(illegalclient));
    }



    @InjectMocks
    private ClientService clientService;
        @Test
        public void testFindAll() throws ServiceException, DaoException {
            // mock the DAO
            ClientDao clientDaoMock = Mockito.mock(ClientDao.class);

            // create test data
            List<Client> expectedClients = new ArrayList<>();
            expectedClients.add(new Client("John", "Doe", "jhon@doe.com",LocalDate.now()));
            expectedClients.add(new Client("Jane", "Doe", "jane@doe.com",LocalDate.now()));
            when(clientService.findAll()).thenReturn(expectedClients);

            // configure the DAO mock
            when(clientDaoMock.findAll()).thenReturn(expectedClients);

            // create the service
            //ClientService clientService = new ClientService(clientDaoMock);

            // call the method
            List<Client> actualClients = clientService.findAll();

            // assert the results
            assertEquals(expectedClients.size(), actualClients.size());
            for (int i = 0; i < expectedClients.size(); i++) {
                Client expectedClient = expectedClients.get(i);
                Client actualClient = actualClients.get(i);
                assertEquals(expectedClient.getNom(), actualClient.getNom());
                assertEquals(expectedClient.getPrenom(), actualClient.getPrenom());
            }

            // verify the DAO method was called
            verify(clientDaoMock, times(1)).findAll();
        }
    }










