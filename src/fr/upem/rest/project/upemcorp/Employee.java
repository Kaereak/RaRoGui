package fr.upem.rest.project.upemcorp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Employee extends UnicastRemoteObject implements Employees {

	public Employee() throws RemoteException {
		super();
		
	}

}
