import java.io.*;
import java.util.*;

public class ProductIO {
    private static final String FILE_NAME = "products.txt";

    public static void saveProducts(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Product p : products) {
                writer.write(p.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return products;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String name = parts[1];
                    int stock = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    products.add(new Product(id, name, stock, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}
