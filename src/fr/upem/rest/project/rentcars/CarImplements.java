package fr.upem.rest.project.rentcars;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("serial")
public class CarImplements extends UnicastRemoteObject implements Car {
	
	private final String brand;
	private final String id;
	private final String model;
	private final int nbDoors;
	private List<Rate> rate = new ArrayList<>();
	private int state;
	private boolean available;
	private int employeeIdForRent;

	public CarImplements(String brand, String model, String id, int state, int nbDoors) throws RemoteException {
		super();
		if((state < 0 || state > 5) && (nbDoors < 0 || nbDoors > 5)){
			throw new IllegalArgumentException();
		}
		
		this.state = state;
		this.brand = brand;
		this.model = model;
		this.id = id;
		available = true;
		this.nbDoors = nbDoors;
	}
	
	public boolean rent(int employeeId){
		if(!available){
			return false;
		}

		employeeIdForRent = employeeId;
		available = false;
		
		return true;
	}
	
	public void returnsCar(int points, String comment) throws RemoteException{
		addRate(employeeIdForRent, points, comment);
		available = true;
	}
	
	public void addRate(int idEmployee, int points, String comment) throws RemoteException {
		Rate r = new Rate(idEmployee, points, comment);
		rate.add(Objects.requireNonNull(r));		
	}
	
	public void setState(int points) throws RemoteException {
		
		if(points < 0 || points > 5){
			throw new IllegalArgumentException();
		}
		
		state = points;
	}
	
	public int getNbDoors(){
		return nbDoors;
	}

	public String getBrand() throws RemoteException {
		return brand;
	}

	public String getModel() throws RemoteException {
		return model;
	}

	public String getId() throws RemoteException {
		return id;
	}

	public List<Rate> getRate() throws RemoteException {
		return rate;
	}

	public int getState() throws RemoteException {
		return state;
	}

	public void setRent(boolean rent) throws RemoteException {
		this.available = rent;
	}

	public boolean getRent() throws RemoteException {
		return available;
	}

	public void setEmployeeID(int employeeID) throws RemoteException {
		this.employeeIdForRent = employeeID;
	}

	public int getEmployeeID() throws RemoteException {
		return employeeIdForRent;
	}


}
