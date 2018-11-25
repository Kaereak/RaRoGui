package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {
	void notifyObs(String msg) throws RemoteException;
}
