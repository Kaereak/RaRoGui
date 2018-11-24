package fr.upem.rest.project.rentcars;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("serial")
public class CarImplements implements Car {

	private final String matriculeCar;
	private final String brand;
	private final String model;
	private final int nbDoors;
	private List<Rate> rate = new ArrayList<>();
	private int state;

	public CarImplements(String brand, String model, String id, int state, int nbDoors) throws RemoteException {
		if((state < 0 || state > 5) && (nbDoors < 0 || nbDoors > 5)){
			throw new IllegalArgumentException();
		}
		this.brand = brand;
		this.state = state;
		this.model = model;
		this.matriculeCar = id;
		this.nbDoors = nbDoors;
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

	public String getModel() throws RemoteException {
		return model;
	}

	public String getMatriculeCar() throws RemoteException {
		return matriculeCar;
	}

	public List<Rate> getRate() throws RemoteException {
		return rate;
	}

	public int getState() throws RemoteException {
		return state;
	}

	public String toString(){
		return matriculeCar + " " + brand + " " + model + " " + "Nb doors : " + nbDoors;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;

		CarImplements c = (CarImplements) o;

		return (this.model).equals(c.model)
				&& (this.matriculeCar).equals(c.matriculeCar)
				&& this.nbDoors == c.nbDoors
				&& this.state == c.state
				&& (this.brand).equals(c.brand);
	}


}
