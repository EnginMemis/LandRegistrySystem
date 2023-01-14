package Models;

public class Owns {
    private int ownerSsn;
    private User owner;
    private int landRegistryId;
    private LandRegistry landRegistry;

    public Owns(User owner, LandRegistry landRegistry) {
        this.owner = owner;
        this.ownerSsn = owner.getSsn();
        this.landRegistry = landRegistry;
        this.landRegistryId = landRegistry.getId();
    }

    public int getOwnerSsn() {
        return ownerSsn;
    }

    public void setOwnerSsn(int ownerSsn) {
        this.ownerSsn = ownerSsn;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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
