package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class RentManagementImplements extends UnicastRemoteObject implements RentManagement {

	/* map pour les locations et gestions de requetes */
	private final Map<String, Queue<Requests>> requests = new HashMap<>();
	private Map<String, Requests> rentedCars = new HashMap<>();
	private final Map<String, Car> mapCars = new HashMap<>();

	public RentManagementImplements(Garages garage) throws RemoteException {
		garage.getList().stream().forEach(car -> {
			try {
				this.requests.put(car.getMatriculeCar(), new LinkedBlockingQueue<>());
			} catch (RemoteException e) {
				throw new IllegalArgumentException();
			}
		});
		garage.getList().stream().forEach(car -> {
			try {
				this.mapCars.put(car.getMatriculeCar(), car);
			} catch (RemoteException e) {
				throw new IllegalArgumentException();
			}
		});
	}
	
	
	@Override
	public void addRequest(Car c, Requests request) throws RemoteException {
		if (requests.containsKey(c.getMatriculeCar())) {
			requests.get(c.getMatriculeCar())
					.add(request);
		} else {
			throw new IllegalArgumentException("fr.upem.rmi.Car not found");
		}
		attributeCar();
	}

	@Override
	public void deleteRequest(Car car, Requests r) throws RemoteException {
		requests.get(car.getMatriculeCar())
                .remove(r);
		attributeCar();
	}
	
	@Override
	public void finishRent(Car car) throws RemoteException {
		rentedCars.get(car.getMatriculeCar()).setStatus(Requests.RequestStatus.TERMINATED);
		mapCars.get(car.getMatriculeCar()).setRented(false);
		attributeCar();
	}

	@Override
	public void attributeCar() throws RemoteException {
		this.requests.entrySet().stream().filter(data -> {
			try {
				return !rentedCars.containsKey(data.getKey())||!mapCars.get(data.getKey()).getRented();
			} catch (RemoteException e2) {
				throw new IllegalArgumentException();
			}
		})
				.filter(data -> data.getValue().size() > 0).forEach(data -> {
					Requests r = (Requests) data.getValue().poll();
					try {
						r.setStatus(Requests.RequestStatus.VALIDATED);
					} catch (RemoteException e1) {
						return;
					}
					rentedCars.put(data.getKey(), r);
					try {
						mapCars.get(data.getKey()).setRented(true);
						r.notifyObserver(data.getKey());
					} catch (RemoteException e) {
						return;
					};
					
				});
		
	}

	@Override
	public void retrieveRentedCar() throws RemoteException {
		rentedCars = rentedCars.entrySet().stream()
				.filter(data -> {
					try {
						return data.getValue().getStatus() != Requests.RequestStatus.TERMINATED;
					} catch (RemoteException e) {
						return false;
					}
				})
				.collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
	}
	
	@Override
	public Requests createRequest(Employee emp, Observer obs) throws RemoteException{
		return new Request(emp, obs);
	}
	
	@Override
	public String toString() {
		return "Voitures en location : "
				+ rentedCars.entrySet().stream()
						.map(data -> "{" + data.getKey().toString() + " " + data.getValue().toString() + "}")
						.reduce((data1, data2) -> data1 + data2 + "\n").get()
				+ '\n' + "Liste des requetes : "
				+ requests.entrySet().stream()
						.map(data -> "{" + data.getKey().toString() + " " + data.getValue().toString() + "}")
						.reduce((data1, data2) -> data1 + data2 + "\n").get();
	}

}
