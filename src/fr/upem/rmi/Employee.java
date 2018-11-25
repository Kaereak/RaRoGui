package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Employee extends Remote {
	public int getId() throws RemoteException;
	
	public String getLastName() throws RemoteException;
	
	public String getFirstName() throws RemoteException;
	
	public int getAge() throws RemoteException;
	
	public String getService() throws RemoteException;

	public String getEmail() throws RemoteException;

	public String getPassword() throws RemoteException;
}
