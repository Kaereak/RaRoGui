package fr.upem.rest.project.rentcars;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Car extends UnicastRemoteObject implements Cars {
	private final String brand;
	private final String id;
	private final String model;
	private int rate;
	private int state;
	private boolean rented;
	private String employeeIdForRent;

	private final LinkedList<String> waitingEmployeesList = new LinkedList<>();

	public Car(String brand, String model, String id, int rate, int state) throws RemoteException {
		super();
		this.brand = brand;
		this.model = model;
		this.id = id;
		this.rate = rate;
		this.state = state;
		rented = false;
	}

	@Override
	public void setRate(int points) throws RemoteException {
		rate = points;
	}

	@Override
	public void setState(int points) throws RemoteException {
		state = points;
	}

	@Override
	public String getBrand() throws RemoteException {
		return brand;
	}

	@Override
	public String getModel() throws RemoteException {
		return model;
	}

	@Override
	public String getId() throws RemoteException {
		return id;
	}

	@Override
	public int getRate() throws RemoteException {
		return rate;
	}

	@Override
	public int getState() throws RemoteException {
		return state;
	}

	@Override
	public void setRent(boolean rent) throws RemoteException {
		this.rented = rent;
	}

	@Override
	public boolean getRent() throws RemoteException {
		return rented;
	}

	@Override
	public void setEmployeeID(String employeeID) throws RemoteException {
		this.employeeIdForRent = employeeID;
	}

	@Override
	public String getEmployeeID() throws RemoteException {
		return employeeIdForRent;
	}

	@Override
	public void addEmployeeToWaitingLine(String employeeID) throws RemoteException {
		waitingEmployeesList.add(employeeID);		
	}
}
