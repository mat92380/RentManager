package epf.rentmanager.service;

import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.validateur.ValidateurClient;
import com.epf.rentmanager.validateur.ValidateurReservation;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReservationServiceTest {

    @Test
    void over7daystest() {
        Reservation illegalreservation = new Reservation(1, 1, 1, LocalDate.parse("2023-04-01"),LocalDate.parse("2023-04-09"));
        assertFalse(ValidateurReservation.over7days(illegalreservation));
    }

}
