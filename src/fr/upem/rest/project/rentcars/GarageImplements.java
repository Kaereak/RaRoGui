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
        listCars.add(new CarImplements("Toyoto", "Yarus", "MFZEFE558", 4, 2));
        listCars.add(new CarImplements("Wolfsvagounne", "Goulfe", "ZFEJOZEP", 3, 5));
        listCars.add(new CarImplements("Teslo", "5", "5ZEFZLP98", 5, 5));
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

    public void removeCar(CarImplements car) throws RemoteException {
        listCars.remove(car);
    }


}
