import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample Data
        List<Product> products = Arrays.asList(
            new Product("P101", "Laptop", 10, 60000),
            new Product("P102", "Monitor", 15, 10000)
        );

        List<Customer> customers = Arrays.asList(
            new Customer("C001", "Surabhi", 100000),
            new Customer("C002", "Ravi", 30000)
        );

        System.out.println("Enter Customer ID:");
        String custId = sc.next();

        Customer currentCustomer = null;
        for (Customer c : customers) {
            if (c.customerId.equals(custId)) {
                currentCustomer = c;
                break;
            }
        }

        if (currentCustomer == null) {
            System.out.println("Invalid Customer!");
            return;
        }

        System.out.println("Enter Product ID:");
        String prodId = sc.next();

        Product currentProduct = null;
        for (Product p : products) {
            if (p.productId.equals(prodId)) {
                currentProduct = p;
                break;
            }
        }

        if (currentProduct == null) {
            System.out.println("Invalid Product!");
            return;
        }

        System.out.println("Enter Quantity:");
        int qty = sc.nextInt();

        if (qty > currentProduct.stock) {
            System.out.println("Not enough stock.");
            return;
        }

        double totalAmount = qty * currentProduct.price;

        if (totalAmount > currentCustomer.creditLimit) {
            System.out.println("Credit limit exceeded.");
            return;
        }

        String orderId = "ORD" + new Random().nextInt(1000);
        Order newOrder = new Order(orderId, custId, prodId, qty, totalAmount);
        currentProduct.stock -= qty;

        System.out.println("Order Placed Successfully!");
        System.out.println("Order ID: " + newOrder.orderId);
        System.out.println("Invoice Amount: ₹" + newOrder.totalAmount);
    }
}
