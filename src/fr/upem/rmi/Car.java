package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Car extends Remote {

	public void addRate(int idEmployee, int points, String comment) throws RemoteException;
	public void setState(int points) throws RemoteException;
	public int getNbDoors() throws RemoteException;
	public String getModel() throws RemoteException;
	public String getMatriculeCar() throws RemoteException;
	public List<Rates> getRate() throws RemoteException;
	public double averageRate() throws RemoteException;
	public int getState() throws RemoteException;
	public String getBrand() throws RemoteException;
	public boolean getRented() throws RemoteException;
	public void setRented(boolean rented) throws RemoteException;
	public int getPriceRent() throws RemoteException;
    public int getPriceSell() throws RemoteException;
}
