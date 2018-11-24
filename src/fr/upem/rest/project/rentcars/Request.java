package fr.upem.rest.project.rentcars;

import fr.upem.rest.project.upemcorp.Employee;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Request {
	private final Employee employee;
	private final String date;
	private RequestStatus status;
	
	public Request(Employee e){
		this.employee = e;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		this.date = df.format(today);
		
		status = RequestStatus.PENDING;
	}
	
	public void nextStep(RequestStatus status){
		if(status == RequestStatus.PENDING){
			this.status = status;
		} else {
			throw new IllegalStateException();
		}
		
	}

	public  RequestStatus getStatus(){
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	@Override
	public String toString(){
		return employee + " " + date + " " + status;
	}
}
