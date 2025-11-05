import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;

public class PlaceOrderForm extends JFrame {
    private JComboBox<String> customerBox, productBox;
    private JTextField qtyField;
    private static List<Customer> customers;
    private static List<Product> products;

    public PlaceOrderForm(List<Customer> cList, List<Product> pList) {
        customers = cList;
        products = pList;

        setTitle("Place Order");
        setSize(350, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(new JLabel("Select Customer:"));
        customerBox = new JComboBox<>();
        for (Customer c : customers) customerBox.addItem(c.customerId);
        add(customerBox);

        add(new JLabel("Select Product:"));
        productBox = new JComboBox<>();
        for (Product p : products) productBox.addItem(p.productId);
        add(productBox);

        add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        add(qtyField);

        JButton placeBtn = new JButton("Place Order");
        add(placeBtn);
        add(new JLabel(""));

        placeBtn.addActionListener(e -> placeOrder());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void placeOrder() {
        String custId = (String) customerBox.getSelectedItem();
        String prodId = (String) productBox.getSelectedItem();
        String qtyStr = qtyField.getText().trim();

        if (qtyStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int qty = Integer.parseInt(qtyStr);

            Customer cust = customers.stream()
                    .filter(c -> c.customerId.equals(custId))
                    .findFirst().orElse(null);

            Product prod = products.stream()
                    .filter(p -> p.productId.equals(prodId))
                    .findFirst().orElse(null);

            if (prod == null || cust == null) return;

            double total = qty * prod.price;

            if (qty > prod.stock) {
                JOptionPane.showMessageDialog(this, "Not enough stock!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (total > cust.creditLimit) {
                JOptionPane.showMessageDialog(this, "Credit limit exceeded!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Reduce stock and credit
            prod.stock -= qty;
            cust.creditLimit -= total;

            // Save updated lists
            ProductIO.saveProducts(products);
            CustomerIO.saveCustomers(customers);

            // Generate order ID
            String orderId = "ORD" + new Random().nextInt(1000);

            // Show confirmation
            JOptionPane.showMessageDialog(this,
                    "Order Placed!\nOrder ID: " + orderId +
                            "\nCustomer: " + cust.name +
                            "\nProduct: " + prod.name +
                            "\nQuantity: " + qty +
                            "\nTotal Amount: ₹" + total +
                            "\nRemaining Credit Limit: ₹" + cust.creditLimit);

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
