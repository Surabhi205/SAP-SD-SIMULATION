import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AddCustomerForm extends JFrame {
    private JTextField idField, nameField, creditField;
    private static List<Customer> customerList;

    public AddCustomerForm(List<Customer> customers) {
        customerList = customers;

        setTitle("Add New Customer");
        setSize(300, 250);
        setLayout(new GridLayout(4, 2, 10, 10));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Labels + Fields
        add(new JLabel("Customer ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Credit Limit:"));
        creditField = new JTextField();
        add(creditField);

        JButton saveButton = new JButton("Save Customer");
        add(saveButton);

        // Empty placeholder
        add(new JLabel(""));

        // Action
        saveButton.addActionListener(e -> saveCustomer());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveCustomer() {
        String id = idField.getText().trim().toUpperCase();
        String name = nameField.getText().trim();
        String creditStr = creditField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || creditStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double credit = Double.parseDouble(creditStr);
            Customer newCust = new Customer(id, name, credit);
            customerList.add(newCust);
            CustomerIO.saveCustomers(customerList);
            JOptionPane.showMessageDialog(this, "Customer added successfully!");
            dispose(); // close the form
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Credit Limit must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
