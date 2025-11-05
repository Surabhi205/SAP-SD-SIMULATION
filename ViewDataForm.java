import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewDataForm extends JFrame {
    public ViewDataForm(List<Customer> customers, List<Product> products) {
        setTitle("View All Data");
        setSize(500, 400);
        setLayout(new GridLayout(2, 1));

        JTextArea customerArea = new JTextArea();
        JTextArea productArea = new JTextArea();

        customerArea.setEditable(false);
        productArea.setEditable(false);

        customerArea.append("Customers:\n");
        for (Customer c : customers) {
            customerArea.append(c.customerId + " | " + c.name + " | ₹" + c.creditLimit + "\n");
        }

        productArea.append("Products:\n");
        for (Product p : products) {
            productArea.append(p.productId + " | " + p.name + " | " + p.stock + " units | ₹" + p.price + "\n");
        }

        add(new JScrollPane(customerArea));
        add(new JScrollPane(productArea));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
