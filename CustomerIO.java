import java.io.*;
import java.util.*;

public class CustomerIO {
    private static final String FILE_NAME = "customers.txt";

    public static void saveCustomers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Customer c : customers) {
                writer.write(c.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> loadCustomers() {
        List<Customer> customers = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return customers;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0];
                    String name = parts[1];
                    double credit = Double.parseDouble(parts[2]);
                    customers.add(new Customer(id, name, credit));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
