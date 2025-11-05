import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AppGUI extends JFrame {

    // 🔁 Shared data lists
   java.util.List<Customer> customers = CustomerIO.loadCustomers();
   java.util.List<Product> products = ProductIO.loadProducts();


    public AppGUI() {
        setTitle("SAP Sales Order Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Buttons
        JButton addCustomerBtn = new JButton("➕ Add Customer");
        JButton addProductBtn = new JButton("➕ Add Product");
        JButton viewDataBtn = new JButton("👁️ View All Data");
        JButton placeOrderBtn = new JButton("🧾 Place Order");
        JButton exitBtn = new JButton("❌ Exit");

        // Add to window
        add(addCustomerBtn);
        add(addProductBtn);
        add(viewDataBtn);
        add(placeOrderBtn);
        add(exitBtn);

        // Button Actions
        addCustomerBtn.addActionListener(e -> new AddCustomerForm(customers));

        addProductBtn.addActionListener(e -> new AddProductForm(products));


        viewDataBtn.addActionListener(e -> new ViewDataForm(customers, products));


        placeOrderBtn.addActionListener(e -> new PlaceOrderForm(customers, products));


        exitBtn.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null); // center the window
        setVisible(true);
    }

    public static void main(String[] args) {
        new AppGUI();
    }
}
