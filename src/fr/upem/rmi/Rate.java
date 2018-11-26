package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Rate extends UnicastRemoteObject implements Rates {
	
	private final int idEmployee;
	private final int rate;
	private final String comment;
	
	public Rate(int idEmployee, int rate, String comment) throws RemoteException{
		super();
		if(rate < 0 || rate > 5){
			throw new IllegalArgumentException();
		}
		
		this.idEmployee = idEmployee;
		this.rate = rate;
		this.comment = comment;
	}
	
	@Override
	public int getIdEmployee() throws RemoteException{
		return idEmployee;
	}
	
	@Override
	public int getRate() throws RemoteException{
		return rate;
	}
	
	@Override
	public String getComment() throws RemoteException{
		return comment;
	}
	
	@Override
	public String toString(){
		return "Rating : " + rate + "\nComment : \n" + comment;
	}
}
