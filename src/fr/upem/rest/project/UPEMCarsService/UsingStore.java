package fr.upem.rest.project.UPEMCarsService;

import fr.upem.rest.project.rentcars.Car;
import fr.upem.rest.project.rentcars.Garages;

import java.util.List;

public class UsingStore {

    private Basket basket;
    private Store store;

    public UsingStore(Garages garage){
        basket = new Basket();
        store = new Store(garage);
    }



}
