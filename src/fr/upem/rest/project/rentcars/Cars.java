package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cars extends Remote {
	String getBrand() throws RemoteException;
	String getId() throws RemoteException;
	String getModel() throws RemoteException;
	int getRate() throws RemoteException;
	int getState() throws RemoteException;
	void setRate(int points) throws RemoteException;
	void setState(int points) throws RemoteException;
	void setRent(boolean rent) throws RemoteException;
	boolean getRent() throws RemoteException;
	void setEmployeeID(String employeeID) throws RemoteException;
	String getEmployeeID() throws RemoteException;
	void addEmployeeToWaitingLine(String employeeID) throws RemoteException;
}
