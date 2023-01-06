package Models;

public class Owns {
    private int ownerSsn;
    private Customer owner;
    private int landRegistryId;
    private LandRegistry landRegistry;

    public Owns(Customer owner, LandRegistry landRegistry) {
        this.owner = owner;
        this.ownerSsn = owner.getSsn();
        this.landRegistry = landRegistry;
        this.landRegistryId = landRegistry.getLandRegistryId();
    }

    public int getOwnerSsn() {
        return ownerSsn;
    }

    public void setOwnerSsn(int ownerSsn) {
        this.ownerSsn = ownerSsn;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public int getLandRegistryId() {
        return landRegistryId;
    }

    public void setLandRegistryId(int landRegistryId) {
        this.landRegistryId = landRegistryId;
    }

    public LandRegistry getLandRegistry() {
        return landRegistry;
    }

    public void setLandRegistry(LandRegistry landRegistry) {
        this.landRegistry = landRegistry;
    }
}
