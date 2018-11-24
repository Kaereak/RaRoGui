package fr.upem.rest.project.upemcorp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ObserverImplements extends UnicastRemoteObject implements Observer {

	public ObserverImplements() throws RemoteException {
		super();
	}

	@Override
	public void notifyObs(String msg) throws RemoteException {
		System.out.println(msg);
	}

}
