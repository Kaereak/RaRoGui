package fr.upem.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;

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

			// EXPORT EMPLOYEE

			EmployeeManagement map = new EmployeeManagementImpl();

            Employee emp0 = new EmployeeImplements(
                    0,
                    "Dupont",
                    "Michel",
                    55,
                    "RH",
                    "dupont@gmail.com",
                    "dupont"
            );
            Employee emp1 = new EmployeeImplements(
                    1,
                    "Martin",
                    "Pauline",
                    25,
                    "Logistique",
                    "martin@gmail.com",
                    "martin"
            );
            Employee emp2 = new EmployeeImplements(
                    2,
                    "Patin",
                    "Patrick",
                    41,
                    "Achat",
                    "patin@gmail.com",
                    "patin"
            );
            Employee emp3 = new EmployeeImplements(
                    3,
                    "Bonaparte",
                    "Napoleon",
                    33,
                    "Invasion",
                    "bonaparte@gmail.com",
                    "bonaparte"
            );

            map.addEmployee(emp0.getId(), emp0);
            map.addEmployee(emp1.getId(), emp1);
            map.addEmployee(emp2.getId(), emp2);
            map.addEmployee(emp3.getId(), emp3);

            Observer obs = new ObserverImplements();
            List<Car> list = garage.searchCarByNbDoors(3);
            rent.addRequest(list.get(1), rent.createRequest(emp0, obs));
            rent.addRequest(list.get(0), rent.createRequest(emp1, obs));
            rent.addRequest(list.get(1), rent.createRequest(emp2, obs));
            rent.addRequest(list.get(1), rent.createRequest(emp3, obs));

            rent.finishRent(list.get(1));

            Naming.rebind("Employee", map);



		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Dead:"+e);
		}
	}
}
