package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EmployeeImplements extends UnicastRemoteObject implements Employee {
	
	private int id;
	private String lastName;
	private String firstName;
	private int age;
	private String service;
	private String email;
	private String password;

    public EmployeeImplements(int id, String lastName, String firstName, int age, String service, String email, String password) throws RemoteException {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.service = service;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public int getId() throws RemoteException{
		return id;
	}
	
	@Override
	public String getLastName() throws RemoteException{
		return lastName;
	}
	
	@Override
	public String getFirstName() throws RemoteException{
		return firstName;
	}
	
	@Override
	public int getAge() throws RemoteException{
		return age;
	}
	
	@Override
	public String getService() throws RemoteException{
		return service;
	}

    @Override
	public String getEmail() throws RemoteException{
        return email;
    }

    @Override
    public String getPassword() throws RemoteException{
        return password;
    }

    @Override
    public String toString() {
        return "EmployeeImplements{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", service='" + service + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
