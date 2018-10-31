package fr.upem.rest.project.rentcars;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observers extends Remote {
	void notifyObs(String msg) throws RemoteException;
}
