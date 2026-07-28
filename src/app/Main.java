package app;

import dao.MenuDAO;
import dao.OrderDAO;
import dao.UserDAO;
import model.Order;
import model.User;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        MenuDAO menuDAO = new MenuDAO();
        OrderDAO orderDAO = new OrderDAO();

        int loggedInUserId = -1;

        while (true) {

            System.out.println("\n========== FOOD ORDERING SYSTEM ==========");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Menu");
            System.out.println("4. Place Order");
            System.out.println("5. View Order History");
            System.out.println("6. Exit");

            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    User user = new User(name, email, password);

                    userDAO.registerUser(user);

                    break;

                case 2:

                    System.out.print("Enter Email: ");
                    email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    password = sc.nextLine();

                    loggedInUserId = userDAO.loginUser(email, password);

                    break;

                case 3:

                    menuDAO.displayMenu();

                    break;

                case 4:

                    if (loggedInUserId == -1) {
                        System.out.println("❌ Please login first!");
                        break;
                    }

                    menuDAO.displayMenu();

                    System.out.print("\nEnter Food ID: ");
                    int foodId = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    double price = menuDAO.getPriceByFoodId(foodId);

                    if (price == 0) {
                        System.out.println("❌ Invalid Food ID!");
                        break;
                    }

                    double total = price * quantity;

String foodName = menuDAO.getFoodNameById(foodId);

System.out.println("\n==============================");
System.out.println("        ORDER BILL");
System.out.println("==============================");
System.out.println("Food Name  : " + foodName);
System.out.println("Price      : ₹" + price);
System.out.println("Quantity   : " + quantity);
System.out.println("------------------------------");
System.out.println("Total Bill : ₹" + total);
System.out.println("==============================");

Order order = new Order(loggedInUserId, foodId, quantity, total);

                    break;

               case 5:

             if (loggedInUserId == -1) {
        System.out.println("❌ Please login first!");
        break;
    }

             orderDAO.viewOrders(loggedInUserId);

             break;
                case 6:

                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice!");
            }
        }
    }
}