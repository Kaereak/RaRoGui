package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Garages extends Remote {
	List<Cars> getListOfCars(String... features) throws RemoteException;
	boolean rentCar(String idCar, String idEmployee) throws RemoteException;
	void rateCar(String id, int points) throws RemoteException;
	void stateOfCar(String id, int points) throws RemoteException;
	void addCar(Cars car) throws RemoteException;
	void returnCar(String idCar, String idEmployee, boolean rateCar_and_State) throws RemoteException;
}
