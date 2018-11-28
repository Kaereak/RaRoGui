package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RentManagement extends Remote{
    public boolean addRequest(Car c, Requests request) throws RemoteException;
    void deleteRequest(Car car, Requests r) throws RemoteException;
    void finishRent(Car car) throws RemoteException;
    void attributeCar() throws RemoteException;
    void retrieveRentedCar() throws RemoteException;
    Requests createRequest(Employee emp, Observer obs) throws RemoteException;
    public int howLongQueue(String car) throws RemoteException;
}
