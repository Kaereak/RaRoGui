package fr.upem.rmi;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Path("/api")
public class Api {

    private UPEMCorp_Impl upem;

    public Api() {
        this.upem = new UPEMCorp_Impl();
    }

    public JSONObject CarToJson(Car car) throws RemoteException {
        JSONObject carJson = new JSONObject();
        carJson.put("brand", car.getBrand());
        carJson.put("model", car.getModel());
        carJson.put("nbDoors", car.getNbDoors());
        carJson.put("rate", car.averageRate());
        carJson.put("state", car.getState());
        carJson.put("priceRent", car.getPriceRent());
        carJson.put("nbRequester", this.upem.getRent().howLongQueue(car.getMatriculeCar()));
        carJson.put("rented", car.getRented());
        carJson.put("id", car.getMatriculeCar());
        return carJson;
    }

    @Path("/user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /* MARCHE PAAAS */
    public Response getUserByEmailAndPassword(
           /* @PathParam("email") String email,
            @PathParam("password") String pass*/
            ) {
        UPEMCorp_Impl upem = new UPEMCorp_Impl();

        // je force l'user dupont
        String email = "dupont@gmail.com";
        String pass = "dupont";

        Collection<Employee> employees = upem.getMap().values();
        System.out.println("collection : " + employees);
        System.out.println("email: " + email);
        System.out.println("pass: " + pass);
        Employee employee = employees.stream()
                .filter((Employee e) -> {
                    try {
                        return email.equals(e.getEmail()) && pass.equals(e.getPassword());
                    } catch (RemoteException e1) {
                        return false;
                    } })
                .findAny()
                .orElse(null);
        System.out.println(employee);

        JSONObject o = new JSONObject();
        try {
            o.put("email", employee.getEmail());
        } catch (RemoteException e) {
            return Response.status((Response.Status.NO_CONTENT)).build();
        }
        return Response.status(Response.Status.OK).entity(o.toString()).build();
    }

    @GET
    @Produces("text/html")
    public String getHello() {
        return "API";
    }

    @Path("/cars")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars() throws RemoteException {
        Garages garage = upem.getGarage();
        List<Car> cars = garage.getList();
        RentManagement rent = upem.getRent();

        JSONArray json = new JSONArray();
        for (Car car: cars) {
            json.put(CarToJson(car));
        }
        return Response.status(Response.Status.OK).entity(json.toString()).build();
    }

    @Path("/rent-car")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserToQueue(String data) throws RemoteException {
        JSONObject received = new JSONObject(data);
        JSONObject toSend = new JSONObject();
        RentManagement rent = upem.getRent();
        Garages garage = upem.getGarage();
        String carID;
        int userID;
        try {
            carID = received.getString("carID");
            userID = received.getInt("userID");
        } catch(JSONException e) {
            return Response.status(422).entity(new JSONObject().toString()).build();
        }
        Car car = garage.searchCarByID(carID);
        Employee employee = upem.getMap().get(userID);
        Requests request = rent.createRequest(employee, new ObserverImplements());
        boolean res = rent.addRequest(car, request);
        System.out.println("addRequest: " + res);
        if (!res) return Response.status(422).entity(new JSONObject().toString()).build();


        int sizeQueue = rent.howLongQueue(carID);
        System.out.println("sizeQueue: " + sizeQueue);
        toSend.put("rentStatus", request.getStatus());
        toSend.put("rentQueuePosition", sizeQueue);

        return Response.status(Response.Status.OK).entity(toSend.toString()).build();
    }

    @Path("/user-requests")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentedCarsByUser(@QueryParam("userID") int userID) throws RemoteException {
        RentManagement rent = this.upem.getRent();
        Garages garage = this.upem.getGarage();
        JSONArray toSend = new JSONArray();

        Map<String, Requests> requests = rent.carsRequested(userID);
        requests.forEach((key, r) -> {
            JSONObject json = new JSONObject();
            try {
                Car car = garage.searchCarByID(key);
                json.put("car", CarToJson(car));
                json.put("status", r.getStatus());
            } catch (RemoteException e) {
            }
            toSend.put(json);
        });
        return Response.status(200).entity(toSend.toString()).build();
    }

    @Path("/end-rent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response setCarFinish(@QueryParam("carID") String carID) throws RemoteException {
        RentManagement rent = this.upem.getRent();
        Garages garage = this.upem.getGarage();
        Car car = garage.searchCarByID(carID);
        rent.finishRent(car);
        JSONObject toSend = new JSONObject();
        toSend.put("result", true);
        return Response.status(200).entity(toSend.toString()).build();
    }

}