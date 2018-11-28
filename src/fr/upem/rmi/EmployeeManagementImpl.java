package fr.upem.rmi;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class EmployeeManagementImpl extends UnicastRemoteObject implements EmployeeManagement {
    private Map<Integer, Employee> map = new HashMap<>();

    public EmployeeManagementImpl() throws RemoteException {
        super();
    }

    @Override
    public Map<Integer, Employee> getMap() throws RemoteException {
        return this.map;
    }

    @Override
    public void addEmployee(int id, Employee emp) throws RemoteException {
        this.map.put(id, emp);
    }


}
