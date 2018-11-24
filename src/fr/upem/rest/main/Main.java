package fr.upem.rest.main;

import fr.upem.rest.project.rentcars.GarageImplements;
import fr.upem.rest.project.rentcars.Garages;
import fr.upem.rest.project.rentcars.RentManagementImplements;
import fr.upem.rest.project.rentcars.Request;
import fr.upem.rest.project.upemcorp.UpemCorp;

import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) throws RemoteException {
        UpemCorp upem = new UpemCorp();
        Garages garageFr = new GarageImplements();

        RentManagementImplements managementFrance = new RentManagementImplements(garageFr);

        managementFrance.addRequest(garageFr.searchCarByNbDoors(2).get(0), new Request(upem.getEmployee(0)));

        managementFrance.attributeCar();
        System.out.println(managementFrance);

        managementFrance.retrieveRentedCar();

        managementFrance.finishRent(garageFr.searchCarByNbDoors(2).get(0));
        System.out.println(managementFrance);

        managementFrance.retrieveRentedCar();


    }
}
