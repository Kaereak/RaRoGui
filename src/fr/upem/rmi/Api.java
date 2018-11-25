package fr.upem.rmi;

import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.rmi.RemoteException;
import java.util.Collection;

@Path("/api")
public class Api {
    @Path("/user")
    // @Path("/user?email={email}&password={password}")
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
}
