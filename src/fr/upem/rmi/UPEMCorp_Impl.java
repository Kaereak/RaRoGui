package fr.upem.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public class UPEMCorp_Impl {
    private Garages garage;
    private RentManagement rent;
    private EmployeeManagement emp;

    public UPEMCorp_Impl() {
        try {
            garage = (Garages) Naming.lookup("Garage");
            rent = (RentManagement) Naming.lookup("Rent");
            emp = (EmployeeManagement) Naming.lookup("Employee");
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.out.println("Client died :" + e);
        }
    }


    public Garages getGarage() {
        return garage;
    }

    public RentManagement getRent() {
        return rent;
    }

    public Map<Integer, Employee> getMap() throws RemoteException {
        return emp.getMap();
    }
}
