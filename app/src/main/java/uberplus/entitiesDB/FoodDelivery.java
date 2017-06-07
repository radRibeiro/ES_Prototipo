package uberplus.entitiesDB;

public class FoodDelivery extends ServiceRequest {
    String foodName;
    int quantity;

    public FoodDelivery( String destinationAddress, String foodName, int quantity) {
        super("", destinationAddress);
        this.foodName = foodName;
        this.quantity = quantity;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

}
