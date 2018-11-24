package fr.upem.rest.project.rentcars;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Rate {
	
	private final int idEmployee;
	private final int rate;
	private final String comment;
	private final String date;
	
	public Rate(int idEmployee, int rate, String comment){
		if(rate < 0 || rate > 5){
			throw new IllegalArgumentException();
		}
		
		this.idEmployee = idEmployee;
		this.rate = rate;
		this.comment = comment;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		this.date = df.format(today);
	}
	
	public int getIdEmployee(){
		return idEmployee;
	}
	
	public int getRate(){
		return rate;
	}
	
	public String getComment(){
		return comment;
	}
	
	public String toString(){
		return "Rating : " + rate + "\nComment : \n" + comment;
	}
	

}
