package fr.upem.rest.project.upemcorp;


import java.rmi.RemoteException;
import java.util.HashMap;  
import java.util.Map; 
import java.util.Objects;

import fr.upem.rest.project.rentcars.CarImplements;

public class UpemCorp {
	
	private HashMap<Integer, Employee> mapEmployees = new HashMap(); 

	public UpemCorp() throws RemoteException{
		/*Set de test*/
		EmployeeImplements rara = new EmployeeImplements(0, "Rara", "Rarara", 3, "Chocolat");
		EmployeeImplements rosa = new EmployeeImplements(2, "Rosa", "Cuit", 12, "Marshmallow");
		EmployeeImplements guigui = new EmployeeImplements(1, "Guigui", "Gras Silva", 3, "Bobun");
		
		addEmployee(rara);
		addEmployee(rosa);
		addEmployee(guigui);
		
	}
	
	public void addEmployee(EmployeeImplements e){
		mapEmployees.put(Objects.requireNonNull(e.getId()), Objects.requireNonNull(e));
	}
	
	public void removeEmployee(EmployeeImplements e){
		mapEmployees.remove(Objects.requireNonNull(e.getId()));
	}
	
	public Employee getEmployee(int id){
		return mapEmployees.get(id);
	}
}
