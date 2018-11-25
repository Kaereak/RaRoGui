package fr.upem.rest.project.UPEMCarsService;

import fr.upem.rest.project.rentcars.Car;
import fr.upem.rest.project.rentcars.Garages;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Store {

    private Garages garage;

    public Store(Garages garage){
        this.garage = Objects.requireNonNull(garage);
    }

    public void removeCar(Car c){
        garage.removeCar(c);
    }

}
