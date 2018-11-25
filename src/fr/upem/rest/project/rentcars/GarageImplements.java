package fr.upem.rest.project.rentcars;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class GarageImplements implements Garages {
    /*liste des voitures existantes*/
    private List<Car> listCars = new ArrayList<>();

    public GarageImplements() throws RemoteException {
        addCar(new CarImplements("Toyota", "Prius", "DF-047-RD", 8, 5, 10000));
        addCar(new CarImplements("Renault", "Capture", "MD-058-DP", 6, 5, 10000));
        addCar(new CarImplements("Toyota", "Prius", "PL-858-LQ", 9, 5, 10000));
        addCar(new CarImplements("Hyundai", "Kona", "PM-200-AF", 8, 3, 10000));
        addCar(new CarImplements("Renault", "Megane", "MO-545-XD", 5, 3, 10000));
        addCar(new CarImplements("Opel", "Zafira", "LO-988-AQ", 8, 5, 10000));
        addCar(new CarImplements("Citroen", "C4", "AX-480-AX", 6, 3, 10000));

    }

    public List<Car> searchCar(Predicate<Car> condition) {
        return listCars.stream().filter(condition).collect(Collectors.toList());
    }

    public List<Car> searchCarByBrand(String... brand) {
        return searchCar(car -> Arrays.asList(brand).contains(car));
    }

    public List<Car> searchCarByNbDoors(int nbDoors) {
        return searchCar(car -> car.getNbDoors() == nbDoors);
    }

    public void addCar(Car car) {

        if (listCars.stream().filter(d -> {
            try {
                return d.getMatriculeCar().equals(car.getMatriculeCar());
            } catch (RemoteException e) {
                return false;
            }
        }).count() == 0) {
            listCars.add(car);
        } else {
            throw new IllegalArgumentException();
        }

    }

    public List<Car> getList() {
        return listCars;
    }

    public void removeCar(Car car) {
        listCars.remove(car);
    }


}
