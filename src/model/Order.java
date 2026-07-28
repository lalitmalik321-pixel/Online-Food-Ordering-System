package model;

public class Order {

    private int userId;
    private int foodId;
    private int quantity;
    private double totalAmount;

    public Order(int userId, int foodId, int quantity, double totalAmount) {
        this.userId = userId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public int getUserId() {
        return userId;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}