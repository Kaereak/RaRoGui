package fr.upem.rest.project.MyBank;

public class Account {

    private int idAccount;
    private String firstName;
    private String lastName;
    private float money;
    private String devise;

    public Account(int idAccount, String firstName, String lastName, float money, String devise){
        this.idAccount = idAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.money = money;
    }

    public void addMoney(float money){
        this.money += money;
    }

    public boolean removeMoney(float money){
        if(this.money - money < 0){
            return false;
        }

        this.money -= money;
        return false;
    }

}
