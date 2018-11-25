package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Predicate;

public interface Garages extends Remote {

    public List<Car> searchCar(Predicate<Car> condition) throws RemoteException;

    public List<Car> searchCarByBrand(String... brand) throws RemoteException;

    public List<Car> searchCarByNbDoors(int nbDoors) throws RemoteException;

    public List<Car> getList() throws RemoteException;
}
