import Models.*;
import Services.*;

import java.sql.SQLException;
import java.util.ArrayList;

import GUI.MainMenu;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService(new DbService());
        PropertyService propertyService = new PropertyService(new DbService());
        LandRegistryService landRegistryService = new LandRegistryService(new DbService());
        PropertyFeatureService propertyFeatureService = new PropertyFeatureService(new DbService());

        try {

        		
        	MainMenu main = new MainMenu();
			main.setVisible(true);

            userService.FinalizeConnections();
            propertyService.FinalizeConnections();
            landRegistryService.FinalizeConnections();
            propertyFeatureService.FinalizeConnections();
        } catch (Exception e) {

        }
    }
}