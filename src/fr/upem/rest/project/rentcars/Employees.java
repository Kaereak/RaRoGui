package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Employees extends Remote {
	public String getName() throws RemoteException;
	public String getId() throws RemoteException;
	public String getEmail() throws RemoteException;
	public int getNumber() throws RemoteException;
	
}
