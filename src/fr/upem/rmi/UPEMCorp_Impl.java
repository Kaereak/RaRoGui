package fr.upem.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class UPEMCorp_Impl {
    private Garages garage;
    private RentManagement rent;
    private Map<Integer, Employee> map;

    public UPEMCorp_Impl() {
        try {
            garage = (Garages) Naming.lookup("Garage");
            rent = (RentManagement) Naming.lookup("Rent");

            map = new HashMap<>();

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
                    "bonapart"
            );

            map.put(emp0.getId(), emp0);
            map.put(emp1.getId(), emp1);
            map.put(emp2.getId(), emp2);
            map.put(emp3.getId(), emp3);

            Observer obs = new ObserverImplements();
            java.util.List<Car> list = garage.searchCarByNbDoors(3);
            for (Car car : list) {
                System.out.println(car.getMatriculeCar() + " " + car.getModel() + " " + car.getNbDoors());
            }
            rent.addRequest(list.get(1), rent.createRequest(emp0, obs));
            rent.addRequest(list.get(0), rent.createRequest(emp1, obs));
            rent.addRequest(list.get(1), rent.createRequest(emp2, obs));
            rent.addRequest(list.get(1), rent.createRequest(emp3, obs));

            rent.finishRent(list.get(1));

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println("Client died :" + e);
        }
    }


    public Garages getGarage() {
        return garage;
    }

    public RentManagement getRent() {
        return rent;
    }

    public Map<Integer, Employee> getMap() {
        return map;
    }
}
