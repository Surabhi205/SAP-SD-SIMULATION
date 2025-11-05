public class Product {
    String productId;
    String name;
    int stock;
    double price;

    public Product(String productId, String name, int stock, double price) {
        this.productId = productId;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
    @Override
    public String toString() {
    return productId + "," + name + "," + stock + "," + price;
}
}
