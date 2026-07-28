package app;

import dao.OrderDAO;

public class Main {

    public static void main(String[] args) {

        OrderDAO dao = new OrderDAO();

        dao.viewOrders();
    }
}