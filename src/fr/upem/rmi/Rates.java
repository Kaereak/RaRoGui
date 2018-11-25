package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rates extends Remote{
	public int getIdEmployee() throws RemoteException;
	public int getRate() throws RemoteException;
	public String getComment() throws RemoteException;
}
