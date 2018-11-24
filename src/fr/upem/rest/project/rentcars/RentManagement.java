package fr.upem.rest.project.rentcars;

public interface RentManagement {
    void addRequest(Car c, Request request);
    void deleteRequest(Car car, Request r);
    void finishRent(Car car);
    void attributeCar();
    void retrieveRentedCar();

}
