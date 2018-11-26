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
        UPEMCorp_Impl upem = new UPEMCorp_Impl();
        Garages garage = upem.getGarage();
        List<Car> cars = garage.getList();
        JSONArray json = new JSONArray();
        for (Car car: cars) {
            JSONObject carJson = new JSONObject();
            carJson.put("brand", car.getBrand());
            carJson.put("model", car.getModel());
            carJson.put("nbDoors", car.getNbDoors());
            carJson.put("rate", car.averageRate());
            carJson.put("state", car.getState());
            carJson.put("rented", car.getRented());
            carJson.put("priceRent", car.getPriceRent());
            json.put(carJson);
        }
        return Response.status(Response.Status.OK)
                .entity(json.toString())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();


    }
}
