import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AddProductForm extends JFrame {
    private JTextField idField, nameField, stockField, priceField;
    private static List<Product> productList;

    public AddProductForm(List<Product> products) {
        productList = products;

        setTitle("Add New Product");
        setSize(300, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels + Fields
        add(new JLabel("Product ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Product Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Stock:"));
        stockField = new JTextField();
        add(stockField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        JButton saveButton = new JButton("Save Product");
        add(saveButton);
        add(new JLabel(""));

        saveButton.addActionListener(e -> saveProduct());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveProduct() {
        String id = idField.getText().trim().toUpperCase();
        String name = nameField.getText().trim();
        String stockStr = stockField.getText().trim();
        String priceStr = priceField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || stockStr.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int stock = Integer.parseInt(stockStr);
            double price = Double.parseDouble(priceStr);
            Product p = new Product(id, name, stock, price);
            productList.add(p);
            ProductIO.saveProducts(productList);
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Stock & Price must be numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
