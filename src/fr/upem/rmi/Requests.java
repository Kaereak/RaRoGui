package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Requests extends Remote{
	public enum RequestStatus {
		NONE, PENDING, VALIDATED, REFUSED, TERMINATED;
	}
	
	public void nextStep(RequestStatus status) throws RemoteException;

	public RequestStatus getStatus() throws RemoteException;

	public void setStatus(RequestStatus status) throws RemoteException;
	
	public int getEmployee() throws RemoteException;
	
	public void notifyObserver(String immatriculation) throws RemoteException;
}
