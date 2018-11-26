package fr.upem.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

@SuppressWarnings("serial")
public class CarImplements extends UnicastRemoteObject implements Car {

    private final String matriculeCar;
    private final String brand;
    private final String model;
    private final int nbDoors;
    private final List<Rates> rate = new ArrayList<>();
    private int state;
    private boolean rented = false;
    private int priceRent;
    private int priceSell;

    public CarImplements(String brand, String model, String id, int state, int nbDoors, int priceRent, int priceSell) throws RemoteException {
        if ((state < 0 || state > 5) && (nbDoors < 0 || nbDoors > 5)) {
            throw new IllegalArgumentException();
        }
        this.brand = brand;
        this.state = state;
        this.model = model;
        this.matriculeCar = id;
        this.nbDoors = nbDoors;
        this.priceSell = priceSell;
        this.priceRent = priceRent;
    }

    @Override
    public void addRate(int idEmployee, int points, String comment) throws RemoteException {
        Rate r = new Rate(idEmployee, points, comment);
        rate.add(Objects.requireNonNull(r));
    }

    @Override
    public void setState(int points) throws RemoteException {

        if (points < 0 || points > 5) {
            throw new IllegalArgumentException();
        }

        state = points;
    }

    @Override
    public int getNbDoors() throws RemoteException {
        return nbDoors;
    }

    @Override
    public String getModel() throws RemoteException {
        return model;
    }

    @Override
    public String getMatriculeCar() throws RemoteException {
        return matriculeCar;
    }

    @Override
    public List<Rates> getRate() throws RemoteException {
        return rate;
    }

    @Override
    public double averageRate() throws RemoteException {
        OptionalDouble optdouble = rate.stream().mapToInt(data -> {
            try {
                return data.getRate();
            } catch (RemoteException e) {
                return 0;
            }
        }).average();
        if (optdouble.isPresent())
            return optdouble.getAsDouble();
        return 5.0;
    }

    @Override
    public int getState() throws RemoteException {
        return state;
    }

    @Override
    public String toString() {
        return matriculeCar + " " + brand + " " + model + " " + "Nb doors : " + nbDoors;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;

        CarImplements c = (CarImplements) o;

        return (this.model).equals(c.model)
                && (this.matriculeCar).equals(c.matriculeCar)
                && this.nbDoors == c.nbDoors
                && this.state == c.state
                && (this.brand).equals(c.brand);
    }

    @Override
    public String getBrand() throws RemoteException {
        return brand;
    }

    @Override
    public boolean getRented() throws RemoteException {
        return rented;
    }

    @Override
    public void setRented(boolean rented) throws RemoteException {
        this.rented = rented;
    }

    @Override
    public int getPriceRent() throws RemoteException {
        return priceRent;
    }

    @Override
    public int getPriceSell() throws RemoteException {
        return priceSell;
    }
}
