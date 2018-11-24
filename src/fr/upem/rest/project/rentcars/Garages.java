package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Predicate;

public interface Garages extends Remote {
	public List<Car> searchCar(List<Car> cars, Predicate<Car> condition);
	public List<Car> searchCarByBrand(List<Car> cars, String... brand);
	public List<Car> searchCarByNbDoors(List<Car> cars, int nbDoors);
	public boolean rentCar(int idCar, int idEmployee) throws RemoteException;
	public void stateOfCar(String id, int points) throws RemoteException;
	public void addCar(CarImplements car) throws RemoteException;
	public void removeCar(CarImplements car) throws RemoteException;
	public void returnCar(String idCar, int points, String comment) throws RemoteException;
}
