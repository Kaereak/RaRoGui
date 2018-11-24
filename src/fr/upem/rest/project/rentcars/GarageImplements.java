package fr.upem.rest.project.rentcars;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class GarageImplements extends UnicastRemoteObject implements Garages {
	private Map<String, CarImplements> mapCars = new HashMap<>();
	private Map<String, LinkedList<Request>> requests = new HashMap<>();

	public GarageImplements() throws RemoteException {
		super();
		/*Créer des maps de test*/
	}
	
	private void addRequest(String idCar, int idEmployee){
		LinkedList<Request> newList = new LinkedList<>();
		newList.add(new Request(idEmployee));
		requests.put(idCar,newList);
	}
	
	private void deleteRequest(String idCar, int idEmployee) {
		LinkedList<Request> tmp = new LinkedList<>();
		tmp = requests.get(idCar);
	}
	
	private void updateRequests(){
		
	}
	
	public List<Car> searchCar(List<Car> cars, Predicate<Car> condition){
		return cars.stream().filter(condition).collect(Collectors.toList());
	}
	
	public List<Car> searchCarByBrand(List<Car> cars, String... brand){
		return searchCar( cars, car -> Arrays.asList(brand).contains(car));
	}
	
	public List<Car> searchCarByNbDoors(List<Car> cars, int nbDoors){
		return searchCar(cars, car -> car.getNbDoors() == nbDoors);
	}


	public boolean rentCar(int idCar, int idEmployee) throws RemoteException {
		return mapCars.get(idCar).rent(idEmployee);
	}

	public void stateOfCar(String id, int points) throws RemoteException {
		CarImplements car = mapCars.get(id);
		car.setState(points);
		mapCars.put(id, car);
	}

	public void addCar(CarImplements car) throws RemoteException {
		mapCars.put(car.getId(), car);
	}
	
	public void removeCar(CarImplements car) throws RemoteException {
		mapCars.remove(car.getId());
	}

	public void returnCar(String idCar, int points, String comment) throws RemoteException {
		mapCars.get(idCar).returnsCar(points, comment);
	}


}
