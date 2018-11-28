package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class GarageImplements extends UnicastRemoteObject implements Garages {
	public GarageImplements() throws RemoteException {
		super();
	}

	/* liste des voitures existantes */
	private final List<Car> listCars = new ArrayList<>();

	@Override
	public List<Car> searchCar(Predicate<Car> condition) throws RemoteException {
		return listCars.stream().filter(condition).collect(Collectors.toList());
	}

	@Override
	@SuppressWarnings("unlikely-arg-type")
	public List<Car> searchCarByBrand(String... brand) throws RemoteException {
		return searchCar(car -> Arrays.asList(brand).contains(car));
	}

	@Override
	public List<Car> searchCarByNbDoors(int nbDoors) throws RemoteException {
		return searchCar(car -> {
			try {
				return car.getNbDoors() == nbDoors;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return false;
		});
	}
	
	public void addCar(Car car) {
		if (listCars.stream().filter(d -> {
			try {
				return d.getMatriculeCar().equals(car.getMatriculeCar());
			} catch (RemoteException e) {
				return false;
			}
		}).count() == 0) {
			listCars.add(car);
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Car> getList() throws RemoteException {
		return listCars;
	}

	@Override
	public Car searchCarByID(String id) throws RemoteException {
		return listCars.stream().filter(data -> {
			try {
				return data.getMatriculeCar().equals(id);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return false;
		}).findAny().orElse(null);
	}

	public void removeCar(CarImplements car) {
		listCars.remove(car);
	}
	
}
