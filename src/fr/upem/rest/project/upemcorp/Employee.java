package fr.upem.rest.project.upemcorp;

import java.rmi.Remote;

public interface Employee extends Remote {
    int getId();
    String getLastName();
    String getFirstName();
    int getAge();
    String getService();
}
