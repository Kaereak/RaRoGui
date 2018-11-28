package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObserverImplements extends UnicastRemoteObject implements Observer {

	public ObserverImplements() throws RemoteException {
		super();
	}

	@Override
	public void notifyObs(String msg) throws RemoteException {
		System.out.println(msg);
	}

}
