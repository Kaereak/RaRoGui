package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Request extends UnicastRemoteObject implements Requests{
	private final Employee employee;
	private final String date;
	private RequestStatus status;
	
	private final Observer observer;

	public Request(Employee e, Observer observer) throws RemoteException {
		this.employee = e;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		this.date = df.format(today);
		this.observer = observer;
		status = RequestStatus.PENDING;
	}

	@Override
	public void nextStep(RequestStatus status) throws RemoteException {
		if (status == RequestStatus.PENDING) {
			this.status = status;
		} else {
			throw new IllegalStateException();
		}
	}

	@Override
	public RequestStatus getStatus() throws RemoteException {
		return status;
	}

	@Override
	public void setStatus(RequestStatus status) throws RemoteException {
		this.status = status;
	}
	
	@Override
	public int getEmployee() throws RemoteException{
		try {
			return employee.getId();
		} catch (Exception e) {
			return 0;
		}
	}
	
	@Override
	public void notifyObserver(String immatriculation) throws RemoteException{
		observer.notifyObs(employee.getFirstName() + " " + employee.getLastName() + " : " + "La voiture immatriculee " + immatriculation + " vous a ete attribuee.");
	}
	
	@Override
	public String toString() {
		return employee + " " + date + " " + status;
	}
}
