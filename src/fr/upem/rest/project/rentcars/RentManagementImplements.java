package fr.upem.rest.project.rentcars;

import fr.upem.rest.project.upemcorp.Employee;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class RentManagementImplements implements RentManagement {

    /*map pour les locations et gestions de requetes*/
    private Map<Car, Queue<Request>> requests = new HashMap<>();
    private Map<Car, Request> rentedCars = new HashMap<>();

    public RentManagementImplements(Garages garage) throws RemoteException {
        garage.getList().stream().forEach(car -> this.requests.put(car, new LinkedBlockingQueue<>()));
    }

    public void addRequest(Car c, Request request) {
        if (requests.containsKey(c)) {
            requests.get(c).add(request);
        } else {
            throw new IllegalArgumentException("Car not found");
        }
    }

    public void deleteRequest(Car car, Request r) {
        requests.get(car).remove(r);
    }


    public void finishRent(Car car) {
        rentedCars.get(car).setStatus(RequestStatus.TERMINATED);
    }

    public void attributeCar() {
        this.requests.entrySet().stream().filter(data -> !rentedCars.containsKey(data.getKey()))
                .filter(data -> data.getValue().size() > 0)
                .forEach(data -> {
                    Request r = data.getValue().poll();
                    r.setStatus(RequestStatus.VALIDATED);
                    rentedCars.put(data.getKey(), r);
                });
    }

    public void retrieveRentedCar() {
        rentedCars = rentedCars.entrySet().stream()
                .filter(data -> data.getValue().getStatus() != RequestStatus.TERMINATED)
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
    }


    public String toString() {
        return rentedCars.entrySet().stream().map(data -> "{" + data.getKey().toString() + " " + data.getValue().toString() + "}")
                .reduce((data1, data2) -> data1 + data2 + "\n").get() +
                requests.entrySet().stream().map(data -> "{" + data.getKey().toString() + " " + data.getValue().toString() + "}")
                        .reduce((data1, data2) -> data1 + data2 + "\n").get();
    }

}
