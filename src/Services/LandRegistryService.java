package Services;

import Contracts.UpdateLandRegistry;
import Models.LandRegistry;
import Models.Property;

import java.sql.*;
import java.util.ArrayList;

public class LandRegistryService implements ILandRegistryService {
    private final DbService dbService;

    public LandRegistryService(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public LandRegistry mapResult(ResultSet resultSet) throws SQLException {
        Integer landRegistryId = resultSet.getInt("land_registry_id");
        Integer buyerSsn = resultSet.getInt("buyer_ssn");
        Integer sellerSsn = resultSet.getInt("seller_ssn");
        double price = resultSet.getDouble("price");
        Date issuedAt = resultSet.getDate("issued_at");
        boolean isActive = resultSet.getBoolean("is_active");
        Integer propertyId = resultSet.getInt("property_id");
        String address = resultSet.getString("address");
        String propertyType = resultSet.getString("property_type");
        double propertyValue = resultSet.getDouble("property_value");
        double propertyArea = resultSet.getDouble("area");

        return new LandRegistry(
                landRegistryId,
                propertyId,
                new Property(
                        propertyId,
                        address,
                        propertyType,
                        propertyValue,
                        propertyArea
                ),
                buyerSsn,
                sellerSsn,
                price,
                issuedAt,
                isActive
        );
    }

    @Override
    public ArrayList<LandRegistry> getAll() throws SQLException {
        ArrayList<LandRegistry> response = new ArrayList<>();
        ResultSet resultSet = dbService.ExecuteQuery("SELECT * FROM land_registry_property ORDER BY land_registry_id");

        while (resultSet.next()) {
            LandRegistry landRegistry = mapResult(resultSet);
            response.add(landRegistry);
        }

        return response;
    }

    @Override
    public LandRegistry get(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM land_registry_property WHERE land_registry_id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return mapResult(resultSet);
        } else {
            return null;
        }
    }

    @Override
    public LandRegistry create(LandRegistry landRegistry) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO land_registry(property_id, buyer_ssn, seller_ssn, price, " +
                        "is_active) VALUES(?,?,?,?,?)");

        preparedStatement.setInt(1, landRegistry.getPropertyId());
        preparedStatement.setInt(2, landRegistry.getBuyerSsn());
        preparedStatement.setInt(3, landRegistry.getSellerSsn());
        preparedStatement.setDouble(4, landRegistry.getPrice());
        preparedStatement.setBoolean(5, landRegistry.isActive());

        preparedStatement.executeUpdate();

        return landRegistry;
    }

    @Override
    public LandRegistry update(Integer id, UpdateLandRegistry newLandRegistry) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE land_registry SET property_id = ?, buyer_ssn = ?, seller_ssn = ?," +
                        "price = ?, issued_at = ?, is_active = ? WHERE property_id = ?");

        preparedStatement.setInt(1, newLandRegistry.propertyId());
        preparedStatement.setInt(2, newLandRegistry.buyerSsn());
        preparedStatement.setInt(3, newLandRegistry.sellerSsn());
        preparedStatement.setDouble(4, newLandRegistry.price());
        preparedStatement.setDate(5, newLandRegistry.issuedAt());
        preparedStatement.setBoolean(6, newLandRegistry.isActive());

        preparedStatement.executeUpdate();

        return new LandRegistry(
                id,
                newLandRegistry.propertyId(),
                newLandRegistry.buyerSsn(),
                newLandRegistry.sellerSsn(),
                newLandRegistry.price(),
                newLandRegistry.issuedAt(),
                newLandRegistry.isActive());
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM land_registry WHERE land_registry_id = ?");
        preparedStatement.setInt(1, id);

        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<LandRegistry> getLands(Integer buyerSSN) throws SQLException{
        ArrayList<LandRegistry> list = new ArrayList<>();
        LandRegistry land;
        Connection connection = this.dbService.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM land_registry_property WHERE buyer_ssn = ?");
        preparedStatement.setInt(1, buyerSSN);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            land = mapResult(resultSet);
            if(land.isActive()){
                list.add(land);
            }
        }
        return list;
    }
    @Override
    public void FinalizeConnections() {
        this.dbService.FinalizeConnections();
    }

}
