package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface Car extends Remote {

	void addRate(int idEmployee, int points, String comment) throws RemoteException;
	void setState(int points) throws RemoteException;
	int getNbDoors();
	String getModel() throws RemoteException;
	String getMatriculeCar() throws RemoteException;
	List<Rate> getRate() throws RemoteException;
	int getState() throws RemoteException;

}
