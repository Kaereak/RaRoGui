package fr.upem.rest.project.rentcars;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Garage extends UnicastRemoteObject implements Garages {
	private final Map<String, Cars> map = new HashMap<>();

	public Garage() throws RemoteException {
		super();
	}

	@Override
	public List<Cars> getListOfCars(String... features) throws RemoteException {
		List<Cars> searchCar = new ArrayList<>();
		boolean match;
		for (Cars car : map.values()) {
			match = false;
			for (int i = 0; i < features.length; i++) {
				if (car.getBrand().contains(features[i]) || car.getModel().contains(features[i])
						|| car.getId().contains(features[i])) {
					match = true;
				}
			}
			if (match)
				searchCar.add(car);
		}
		return searchCar;
	}

	@Override
	public boolean rentCar(String idCar, String idEmployee) throws RemoteException {
		if (!map.containsKey(idCar))
			return false;
		Cars car = map.get(idCar);
		if (!car.getRent()) {
			car.setEmployeeID(idEmployee);
			car.setRent(true);
			return true;
		} else {
			car.addEmployeeToWaitingLine(idEmployee);
			return false;
		}
	}

	@Override
	public void rateCar(String id, int points) throws RemoteException {
		Cars car = map.get(id);
		car.setRate(max_min_points(points));
		map.put(id, car);
	}

	@Override
	public void stateOfCar(String id, int points) throws RemoteException {
		Cars car = map.get(id);
		car.setState(max_min_points(points));
		map.put(id, car);
	}

	@Override
	public void addCar(Cars car) throws RemoteException {
		map.put(car.getId(), car);
	}

	private int max_min_points(int points) {
		if (points > 10)
			return 10;
		if (points < 0)
			return 0;
		return points;
	}

	@Override
	public void returnCar(String idCar, String idEmployee, boolean rateCar_and_State) throws RemoteException {
		if (!map.containsKey(idCar))
			return;
		Cars car = map.get(idCar);
		if(!car.getRent() || !car.getEmployeeID().equals(idEmployee))
			return;
		car.setRent(false);
		car.setEmployeeID("");
	}
}
