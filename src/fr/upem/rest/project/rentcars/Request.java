package fr.upem.rest.project.rentcars;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Request {
	private final int idCustomer;
	private final String date;
	private RequestStatus status;
	
	public Request(int idCustomer){
		this.idCustomer = idCustomer;	
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
	
}
