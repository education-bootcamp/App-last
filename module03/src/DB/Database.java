package DB;

import Entity.Customer;

import java.util.ArrayList;

public class Database {
    public static ArrayList<Customer> customerTable = new ArrayList<Customer>();
    static {
        customerTable.add(
                new Customer("c001","KAmal","galle",20000));
        customerTable.add(
                new Customer("c002","Amal","galle",30000));
        customerTable.add(
                new Customer("c003","Nimal","panadura",25000));
        customerTable.add(
                new Customer("c004","Wimal","galle",20000));
        customerTable.add(
                new Customer("c005","mal","colombo",10000));
        customerTable.add(
                new Customer("c006","namal","galle",78700));

    }
}
