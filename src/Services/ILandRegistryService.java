package Services;

import Contracts.UpdateLandRegistry;
import Models.LandRegistry;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ILandRegistryService extends IService<LandRegistry, Integer, UpdateLandRegistry> {
    public ArrayList<LandRegistry> getPropertyID(Integer buyerSSN) throws SQLException;
}
