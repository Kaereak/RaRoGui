package fr.upem.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface EmployeeManagement  extends Remote {
    Map<Integer, Employee> getMap() throws RemoteException;
    void addEmployee(int id, Employee emp) throws  RemoteException;
}
