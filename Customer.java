public class Customer {
    String customerId;
    String name;
    double creditLimit;

    public Customer(String customerId, String name, double creditLimit) {
        this.customerId = customerId;
        this.name = name;
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return customerId + "," + name + "," + creditLimit;
    }
}
