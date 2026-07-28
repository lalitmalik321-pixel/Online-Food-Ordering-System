package app;

import dao.OrderDAO;
import model.Order;

public class Main {

    public static void main(String[] args) {

        Order order = new Order(
                1,      // User ID
                2,      // Food ID (Pizza)
                2,      // Quantity
                500.0   // Total Amount
        );

        OrderDAO dao = new OrderDAO();

        dao.placeOrder(order);
    }
}