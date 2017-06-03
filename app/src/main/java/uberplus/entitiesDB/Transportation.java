package uberplus.entitiesDB;

public class Transportation extends ServiceRequest {
    boolean isPrivate;

    public Transportation(String originAddress, String destinationAddress, boolean isPrivate) {
        super(originAddress, destinationAddress,"TRANSPORTATION");
        this.isPrivate = isPrivate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
