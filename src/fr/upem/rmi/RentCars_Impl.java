package fr.upem.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RentCars_Impl {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			GarageImplements garage = new GarageImplements();
			Car car1 = new CarImplements("Toyota", "Prius", "DF-047-RD", 8, 5, 40,10000);
			Car car2 = new CarImplements("Renault", "Capture", "MD-058-DP", 6, 5, 80,10000);

			car1.addRate(2, 4, "C'est cool");
			car1.addRate(2, 2, "C'est pikachu");
			car1.addRate(2, 1, "C'est naze");
			car2.addRate(2, 0, "C'est nul");
			car2.addRate(2, 5, "C'est Psykokwak");
			car2.addRate(2, 3, "C'est moi");

			garage.addCar(car1);
			garage.addCar(car2);
			garage.addCar(new CarImplements("Toyota", "Prius", "PL-858-LQ", 9, 5,10,10000));
			garage.addCar(new CarImplements("Hyundai", "Kona", "PM-200-AF", 8, 3,20,10000));
			garage.addCar(new CarImplements("Renault", "Megane", "MO-545-XD", 5, 3,70,10000));
			garage.addCar(new CarImplements("Opel", "Zafira", "LO-988-AQ", 8, 5,60,10000));
			garage.addCar(new CarImplements("Citroen", "C4", "AX-480-AX", 6, 3,100,10000));

			Naming.rebind("Garage", garage);
			RentManagementImplements rent = new RentManagementImplements(garage);
			
			Naming.rebind("Rent", rent);
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Dead:"+e);
		}
	}
}
