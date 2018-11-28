package fr.upem.rmi;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;

@Path("/api")
public class Api {

    private UPEMCorp_Impl upem;

    public Api() {
        this.upem = new UPEMCorp_Impl();
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
            JSONObject carJson = new JSONObject();
            carJson.put("brand", car.getBrand());
            carJson.put("model", car.getModel());
            carJson.put("nbDoors", car.getNbDoors());
            carJson.put("rate", car.averageRate());
            carJson.put("state", car.getState());
            carJson.put("priceRent", car.getPriceRent());
            carJson.put("nbRequester", rent.howLongQueue(car.getMatriculeCar()));
            carJson.put("rented", car.getRented());
            carJson.put("id", car.getMatriculeCar());
            json.put(carJson);
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
}
