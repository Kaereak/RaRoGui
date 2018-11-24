package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface Car extends Remote {
	public boolean rent(int employeeId);
	public void returnsCar(int points, String comment) throws RemoteException;
	public void addRate(int idEmployee, int points, String comment) throws RemoteException;
	public void setState(int points) throws RemoteException;
	public int getNbDoors();
	public String getBrand() throws RemoteException;
	public String getModel() throws RemoteException;
	public String getId() throws RemoteException;
	public List<Rate> getRate() throws RemoteException;
	public int getState() throws RemoteException;
	public void setRent(boolean rent) throws RemoteException;
	public boolean getRent() throws RemoteException;
	public void setEmployeeID(int employeeID) throws RemoteException;
	public int getEmployeeID() throws RemoteException;
}
