package com.epf.rentmanager.ui;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.AppConfiguration;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

public class test {
    public static void main(String[] args){
        //System.out.println(new Client(12, "matheo","allard","mallard@oxyl", LocalDate.now()));
        //VehicleService vehicleService = VehicleService.getInstance();
        /*try{
            //System.out.println(ClientService.getInstance().findById(2));
            //System.out.println(ClientService.getInstance().findAll());
            //System.out.println(ClientService.getInstance().count());
            //System.out.println(VehicleService.getInstance().findById(2));
            //System.out.println(VehicleService.getInstance().findAll());
            //System.out.println(VehicleService.getInstance().count());
            //System.out.println(ReservationService.getInstance().findAll());
            //System.out.println(ReservationService.getInstance().findResaByClientId(1));


        }catch (ServiceException e){
            e.printStackTrace();
        }*/
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfiguration.class);
        ClientService clientService = context.getBean(ClientService.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
    }
}
