import Constants.DatabaseConnectionString;
import Models.Customer;
import Services.CustomerService;
import Services.DbService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService(new DbService());

        ArrayList<Customer> allCustomers = customerService.getAll();

        if (allCustomers == null) {
            System.out.println("Kullanıcılar çekilemedi.");
        } else {
            for (Customer customer : allCustomers) {
                System.out.println(customer.getName() + " - " + customer.getSurname() + " - " + customer.getSsn());
            }
        }
    }
}