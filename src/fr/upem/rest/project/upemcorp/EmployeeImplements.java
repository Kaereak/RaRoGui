package fr.upem.rest.project.upemcorp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

@SuppressWarnings("serial")
public class EmployeeImplements extends UnicastRemoteObject implements Employee {
	
	private int id;
	private String lastName;
	private String firstName;
	private int age;
	private String service;

	public EmployeeImplements(int id, String lastName, String firstName, int age, String service) throws RemoteException {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.service = service;
		
	}
	
	public int getId(){
		return id;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public int getAge(){
		return age;
	}
	
	public String getService(){
		return service;
	}

}
