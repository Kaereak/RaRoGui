package fr.upem.rest.project.UPEMCarsService;

import fr.upem.rest.project.rentcars.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Basket {
    private List<Car> cars = new ArrayList<>();

    public Basket() {

    }

    public void addCar(Car c) {
        this.cars.add(Objects.requireNonNull(c));
    }

    public void removeCar(Car c) {
        cars.remove(c);
    }

    public int totalPrice() {
//        return cars.stream().collect(Collectors.summingInt(l -> l.getPrice()));
        return cars.stream().map(data -> data.getPrice()).reduce((a,b) -> a+b).orElse(0);
}


}
