public class Order {
    String orderId;
    String customerId;
    String productId;
    int quantity;
    double totalAmount;

    public Order(String orderId, String customerId, String productId, int quantity, double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }
}
