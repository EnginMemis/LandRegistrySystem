import Contracts.UpdateCustomer;
import Contracts.UpdateLandRegistry;
import Contracts.UpdateProperty;
import Contracts.UpdatePropertyFeature;
import Models.Customer;
import Models.LandRegistry;
import Models.Property;
import Models.PropertyFeature;
import Services.*;


import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService(new DbService());
        PropertyService propertyService = new PropertyService(new DbService());
        LandRegistryService landRegistryService = new LandRegistryService(new DbService());
        PropertyFeatureService propertyFeatureService = new PropertyFeatureService(new DbService());


        try {
/*            ArrayList<Customer> allCustomers = null;
            Customer nisa = null;
            Customer nisaUpdated = null;
            Customer engin = null;

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

            engin = customerService.create(new Customer(1010104, "Engin", "Memiş",
                    new Date(1999, 10, 10), "Male", "05555555555",
                    "engin.memis@example.com", "asdasdasdasd", 17000));

            if (engin != null) {
                System.out.println("Engin kardeşim: " + engin.getSsn());
            } else {
                System.out.println("Engini koyamadık :(");
            }

            customerService.delete(1010104);   //*/

/*            ArrayList<Property> allProperty = null;
            Property property = null;
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
            }  //  */

/*            LandRegistry landRegistry = null;
            ArrayList<LandRegistry> landRegistries = null;
            landRegistries = landRegistryService.getAll();

            for (LandRegistry lr : landRegistries) {
                System.out.println(lr.getLandRegistryId() + "-" + lr.getPropertyId()
                        + "-" + lr.getPrice() + "-" + lr.getDate());
                System.out.println(lr.getProperty().getPropertyAddress() + "-" + lr.getProperty().getPropertyType());
            }

            System.out.println("----");
            landRegistry = landRegistryService.get(1);

            if (landRegistry != null) {
                System.out.println(landRegistry.getLandRegistryId() + "-" + landRegistry.getPropertyId()
                        + "-" + landRegistry.getPrice() + "-" + landRegistry.getDate());
                System.out.println(landRegistry.getProperty().getPropertyAddress() + "-"
                        + landRegistry.getProperty().getPropertyType());
            } else {
                System.out.println("Bu ID'ye sahip tapu kaydı yok!");
            }

/*            System.out.println("----");

            landRegistry = landRegistryService.create(new LandRegistry(500, 2, 1000,
                    new Date(2023, 1, 7)));

            if (landRegistry != null) {
                System.out.println(landRegistry.getLandRegistryId() + "-" + landRegistry.getPropertyId()
                        + "-" + landRegistry.getPrice() + "-" + landRegistry.getDate());
            } else {
                System.out.println("Oluşturamadık abi tapuyu :(");
            }

            System.out.println("----");

            landRegistry = landRegistryService.update(500, new UpdateLandRegistry(200, 1000000, new Date(1999, 10, 20)));

            if (landRegistry != null) {
                System.out.println(landRegistry.getLandRegistryId() + "-" + landRegistry.getPropertyId()
                        + "-" + landRegistry.getPrice() + "-" + landRegistry.getDate());
            } else {
                System.out.println("Güncelleyemedik abi :(");
            }

            landRegistryService.delete(500);   //  */

/*            ArrayList<PropertyFeature> propertyFeatures = null;
            PropertyFeature propertyFeature = null;
            propertyFeatures = propertyFeatureService.getAll();

            if (propertyFeatures != null) {
                for (PropertyFeature pf : propertyFeatures) {
                    System.out.println(pf.getFeatureId() + "-" + pf.getFeatureTitle() + "-" + pf.getFeatureValue());
                }
            }

            propertyFeature = propertyFeatureService.get(300);

            if (propertyFeature != null)
            System.out.println(propertyFeature.getFeatureId() + "-"
                    + propertyFeature.getFeatureTitle() + "-" + propertyFeature.getFeatureValue());
            else
                System.out.println("Böyle bir özellik yok he!");

            propertyFeature = propertyFeatureService.create(
                    new PropertyFeature(65, 2, "Balkon Sayısı", "4"));

            if (propertyFeature != null)
                System.out.println(propertyFeature.getFeatureId() + "-"
                        + propertyFeature.getFeatureTitle() + "-" + propertyFeature.getFeatureValue());
            else
                System.out.println("Özelliği oluşturamadım :(");

            propertyFeature = propertyFeatureService.update(64, new UpdatePropertyFeature(
                    123, "Koridor uzunluğu", "20 metre"
            ));

            if (propertyFeature != null)
                System.out.println(propertyFeature.getFeatureId() + "-"
                        + propertyFeature.getFeatureTitle() + "-" + propertyFeature.getFeatureValue());
            else
                System.out.println("Güncelleme başarısız :(");

            propertyFeatureService.delete(63);
            propertyFeatureService.delete(65);  //  */

            customerService.FinalizeConnections();
            propertyService.FinalizeConnections();
            landRegistryService.FinalizeConnections();
            propertyFeatureService.FinalizeConnections();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}