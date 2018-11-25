package fr.upem.rest.project.upemcorp;


import java.rmi.RemoteException;
import java.util.HashMap;  
import java.util.Map; 
import java.util.Objects;

import fr.upem.rest.project.rentcars.CarImplements;

public class UpemCorp {
	
	private Map<Integer, Employee> mapEmployees = new HashMap();

	public UpemCorp() throws RemoteException{
		/*Set de test*/
		Employee emp0 = new EmployeeImplements(0, "Dupont", "Michel", 55, "RH");
		Employee emp1 = new EmployeeImplements(1, "Martin", "Pauline", 25, "Logistique");
		Employee emp2 = new EmployeeImplements(2, "Patin", "Patrick", 41, "Achat");
		Employee emp3 = new EmployeeImplements(3, "Bonaparte", "Napoleon", 33, "Invasion");
		
		addEmployee(emp0);
		addEmployee(emp1);
		addEmployee(emp2);
		addEmployee(emp3);

		
	}
	
	public void addEmployee(Employee e){
		mapEmployees.put(Objects.requireNonNull(e.getId()), Objects.requireNonNull(e));
	}
	
	public void removeEmployee(Employee e){
		mapEmployees.remove(Objects.requireNonNull(e.getId()));
	}
	
	public Employee getEmployee(int id){
		return mapEmployees.get(id);
	}

	public Map<Integer, Employee> getMapEmployee(){
		return mapEmployees;
	}
}
