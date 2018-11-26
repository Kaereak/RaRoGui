package fr.upem.rmi;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Path("/")
public class RentalCars {
    @Context
    ServletContext servletContext;

    @Path("/{path: .+}")
    @GET
    public InputStream getFile(@PathParam("path") String path) {
        try {
            String base = servletContext.getRealPath("/include");
            System.out.println(base);
            File f = new File(String.format("%s/%s", base, path));
            return new FileInputStream(f);
        } catch (FileNotFoundException e) {
            // log the error?
            return null;
        }
    }
}
