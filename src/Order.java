import java.util.Comparator;

public class Order {

    private Customer customer;
    private int date;
    private List<VideoGame> orderContents;
    private int shippingSpeed;
    private int priority; //TODO: ask her about how we calculate priority
    private boolean shippingStatus;

    public Order(Customer customer, int date, List<VideoGame> orderContents, int shippingSpeed, boolean shippingStatus) {

        this.customer = customer;
        this.date = date;
        this.orderContents = orderContents;
        this.shippingSpeed = shippingSpeed;
        this.priority = date / shippingSpeed;
        this.shippingStatus = shippingStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public List<VideoGame> getOrderContents() {
        return orderContents;
    }

    public void setOrderContents(List<VideoGame> orderContents) {
        this.orderContents = orderContents;
    }

    public int getShippingSpeed() {
        return shippingSpeed;
    }

    public void setShippingSpeed(int shippingSpeed) {
        this.shippingSpeed = shippingSpeed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(boolean shippingStatus) {
        this.shippingStatus = shippingStatus;
    }


}

class OrderComparator implements Comparator<Order> {
    public int compare(Order order1, Order order2) {
        return order1.getPriority() - order2.getPriority();
    }
}
//Should also contain a Comparator class with a compare method to determine priority