import Contracts.UpdateCustomer;
import Contracts.UpdateProperty;
import Models.Customer;
import Models.Property;
import Services.CustomerService;
import Services.PropertyService;
import Services.DbService;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService(new DbService());
        PropertyService propertyService = new PropertyService(new DbService());

        ArrayList<Customer> allCustomers = null;
        ArrayList<Property> allProperty = null;
        Customer nisa = null;
        Customer nisaUpdated = null;
        Customer engin = null;
        Property property = null;
        try {
            allCustomers = customerService.getAll();
            if (allCustomers == null) {
                System.out.println("Kullanıcılar çekilemedi.");
            } else {
                for (Customer customer : allCustomers) {
                    System.out.println(customer.getName() + " - " + customer.getSurname() + " - " + customer.getSsn());
                }
            }

            nisa = customerService.get(1010102);
            if (nisa != null) {
                System.out.println("Askm nisammm <3<3<3 " + nisa.getSsn());

                nisaUpdated = customerService.update(nisa.getSsn(),
                        new UpdateCustomer(
                                nisa.getName(),
                                nisa.getSurname(),
                                nisa.getBirthDate(),
                                nisa.getGender(),
                                nisa.getPhoneNumber(),
                                nisa.getCustomerEmail(),
                                "Anıl askmın kalbii <3<3",
                                3000000));
                if (nisaUpdated != null) {
                    System.out.println(nisaUpdated.getSsn() + " - " + nisaUpdated.getCustomerAddress());
                } else {
                    System.out.println("Güncelleme işlemi başarısız :(");
                }
            } else {
                System.out.println("Nisayı bulamadım bilader!!");
            }

/*            engin = customerService.create(new Customer(1010104, "Engin", "Memiş",
                    new Date(1999, 10, 10), "Male", "05555555555",
                    "engin.memis@example.com", "asdasdasdasd", 17000));

            if (engin != null) {
                System.out.println("Engin kardeşim: " + engin.getSsn());
            } else {
                System.out.println("Engini koyamadık :(");
            }   //*/

            // customerService.delete(1010104);

            System.out.println("Son durum:");
            allCustomers = customerService.getAll();
            if (allCustomers == null) {
                System.out.println("Kullanıcılar çekilemedi.");
            } else {
                for (Customer customer : allCustomers) {
                    System.out.println(customer.getName() + " - " + customer.getSurname() + " - " + customer.getSsn());
                }
            }

            property = propertyService.create(new Property(123, "Gungoren", "Bina", 1200000.23, 150.2));
            if(property != null){
                System.out.println("Yeni Bina:" + property.getPropertyAddress());
            }
            else{
                System.out.println("Hata!");
            }

            System.out.println("Son Binalar:");
            allProperty = propertyService.getAll();
            if(allProperty != null){
                for(Property property1 : allProperty){
                    System.out.println(property1.getPropertyAddress());
                }
            }
            else{
                System.out.println("Binalar Cekilemedi!");
            }

            customerService.FinalizeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}