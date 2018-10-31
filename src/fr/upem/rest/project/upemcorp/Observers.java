package fr.upem.rest.project.upemcorp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observers extends Remote {
	void notifyObs(String msg) throws RemoteException;
}
