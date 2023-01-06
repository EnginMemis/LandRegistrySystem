import Constants.DatabaseConnectionString;
import Models.Customer;
import Services.CustomerService;
import Services.DbService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService(new DbService());

        ArrayList<Customer> allCustomers = null;
        Customer nisa = null;
        Customer engin = null;
        try {
            allCustomers = customerService.getAll();
            nisa = customerService.get(1010102);
            engin = customerService.create(new Customer(1010104, "Engin", "Memiş",
                    new Date(1999, 10, 10), "Male", "05555555555",
                    "engin.memis@std.yildiz.edu.tr", "asdasdasdasd"));

            customerService.FinalizeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (allCustomers == null) {
            System.out.println("Kullanıcılar çekilemedi.");
        } else {
            for (Customer customer : allCustomers) {
                System.out.println(customer.getName() + " - " + customer.getSurname() + " - " + customer.getSsn());
            }
        }

        if (nisa != null) {
            System.out.println("Askm nisammm <3<3<3 " + nisa.getSsn());
        } else {
            System.out.println("Nisayı bulamadım bilader!!");
        }

        if (engin != null) {
            System.out.println("Engin kardeşim: " + engin.getSsn());
        } else {
            System.out.println("Engini koyamadık :(");
        }
    }
}