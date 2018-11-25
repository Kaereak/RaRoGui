package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public interface Garages extends Remote {

    void addCar(Car car);

    List<Car> searchCar(Predicate<Car> condition);

    List<Car> searchCarByBrand(String... brand);

    List<Car> searchCarByNbDoors(int nbDoors);

    void removeCar(Car car);

    List<Car> getList();
}
