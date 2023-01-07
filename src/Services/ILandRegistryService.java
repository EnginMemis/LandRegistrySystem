package Services;

import Contracts.UpdateLandRegistry;
import Models.LandRegistry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ILandRegistryService extends IService<LandRegistry, Integer, UpdateLandRegistry> {
}
